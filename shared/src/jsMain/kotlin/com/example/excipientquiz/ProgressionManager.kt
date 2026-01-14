package com.example.excipientquiz

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

actual object ProgressionManager {

    private val settings: Settings by lazy { createSettings() }

    private fun getTierKey(quizMode: String) = "tier_$quizMode"
    private fun getTimeAttackUnlockKey(qType: PropertyType, aType: PropertyType) = "time_attack_${qType}_$aType"
    private fun getSurvivalCompleteKey(quizMode: String, qType: PropertyType, aType: PropertyType) = "survival_complete_${quizMode}_${qType}_$aType"
    private fun getSpeedrunScoreKey(quizMode: String, qType: PropertyType, aType: PropertyType) = "speedrun_score_${quizMode}_${qType}_$aType"
    private fun getSpeedrunTimeKey(quizMode: String, qType: PropertyType, aType: PropertyType) = "speedrun_time_${quizMode}_${qType}_$aType"

    actual fun getProgression(quizMode: String): Progression {
        val tierName = settings.getString(getTierKey(quizMode), ProgressionTier.LOCKED.name)
        val tier = ProgressionTier.valueOf(tierName ?: ProgressionTier.LOCKED.name)
        return Progression(tier)
    }

    actual fun updateProgression(quizMode: String, score: Int, time: Long, wasSuccessful: Boolean): ProgressionTier {
        val currentTier = getProgression(quizMode).tier
        if (!wasSuccessful) return currentTier

        val newTier = when (currentTier) {
            ProgressionTier.LOCKED -> ProgressionTier.ALTERNATIVE_NAMES
            ProgressionTier.ALTERNATIVE_NAMES -> ProgressionTier.FULLY_UNLOCKED
            ProgressionTier.FULLY_UNLOCKED -> ProgressionTier.FULLY_UNLOCKED
        }

        if (newTier.ordinal > currentTier.ordinal) {
            settings[getTierKey(quizMode)] = newTier.name
        }

        return newTier
    }

    actual fun getHighScore(key: String): Long {
        return settings.getLong(key, 0L)
    }

    actual fun setHighScore(key: String, score: Long) {
        settings[key] = score
    }

    actual fun getHighScoreString(key: String): String {
        return settings.getString(key, "")
    }

    actual fun setHighScoreString(key: String, value: String) {
        settings[key] = value
    }

    private fun getEffectiveTier(quizModes: Set<String>): ProgressionTier {
        val relevantCategories = if (quizModes.contains("All Excipients")) {
            com.example.excipientquiz.quizModes.keys.filter { it != "All Excipients" && it != "Other" }
        } else {
            quizModes.filter { it != "Other" }
        }

        if (relevantCategories.isEmpty()) return ProgressionTier.FULLY_UNLOCKED

        return relevantCategories
            .map { getProgression(it).tier }
            .minOrNull() ?: ProgressionTier.LOCKED
    }

    private fun getRequiredTier(isMultiOrAll: Boolean, qType: PropertyType, aType: PropertyType): ProgressionTier {
        val props = setOf(qType, aType)
        val isBasePair = props == setOf(PropertyType.NAME, PropertyType.STRUCTURE)
        
        val tier2Props = setOf(PropertyType.NAME, PropertyType.STRUCTURE, PropertyType.ALTERNATIVE_NAME, PropertyType.MOLECULE_TYPE)
        val isTier2Pair = tier2Props.containsAll(props)

        val baseRequirement = when {
            isBasePair -> ProgressionTier.LOCKED
            isTier2Pair -> ProgressionTier.ALTERNATIVE_NAMES
            else -> ProgressionTier.FULLY_UNLOCKED
        }
        
        return if (isMultiOrAll) {
            when (baseRequirement) {
                ProgressionTier.LOCKED -> ProgressionTier.ALTERNATIVE_NAMES
                ProgressionTier.ALTERNATIVE_NAMES -> ProgressionTier.FULLY_UNLOCKED
                else -> ProgressionTier.FULLY_UNLOCKED
            }
        } else {
            baseRequirement
        }
    }

    actual fun isPermanentlyDisabled(qType: PropertyType, aType: PropertyType): Boolean {
        if (qType == aType) return true

        val props = setOf(qType, aType)

        // Define the allowed bidirectional pairs
        val allowedPairs = setOf(
            setOf(PropertyType.NAME, PropertyType.STRUCTURE),
            setOf(PropertyType.NAME, PropertyType.ALTERNATIVE_NAME),
            setOf(PropertyType.NAME, PropertyType.FUNCTION),
            setOf(PropertyType.NAME, PropertyType.MOLECULE_TYPE),
            setOf(PropertyType.STRUCTURE, PropertyType.ALTERNATIVE_NAME),
            setOf(PropertyType.STRUCTURE, PropertyType.FUNCTION),
            setOf(PropertyType.ALTERNATIVE_NAME, PropertyType.FUNCTION)
        )

        // Check if the current pair is in the allowed list
        if (allowedPairs.contains(props)) return false

        // Check for the specific one-way pair: Alternative name --> molecule type
        if (qType == PropertyType.ALTERNATIVE_NAME && aType == PropertyType.MOLECULE_TYPE) {
            return false
        }

        // Everything else is disabled
        return true
    }

    actual fun isPlayable(quizModes: Set<String>, qType: PropertyType, aType: PropertyType, gameMode: GameMode): Boolean {
        if (isPermanentlyDisabled(qType, aType)) return false
        if (qType == aType) return false
        if (gameMode == GameMode.EXCIPIENT_SPEEDRUN && !isTimeAttackUnlocked(qType, aType)) return false

        if (quizModes.contains("All Excipients")) {
            val allOtherCategories = com.example.excipientquiz.quizModes.keys.filter { it != "All Excipients" && it != "Other" }
            
            val canPlayAllExcipientsAtAll = allOtherCategories.all { getProgression(it).tier >= ProgressionTier.ALTERNATIVE_NAMES }
            if (!canPlayAllExcipientsAtAll) return false
            
            val requiredTierForAttempt = getRequiredTier(isMultiOrAll = true, qType, aType)
            
            if (requiredTierForAttempt == ProgressionTier.FULLY_UNLOCKED) {
                val allOthersFullyUnlocked = allOtherCategories.all { getProgression(it).tier == ProgressionTier.FULLY_UNLOCKED }
                if (!allOthersFullyUnlocked) return false
            }
        }

        val effectiveTier = getEffectiveTier(quizModes)
        val isMultiOrAll = quizModes.size > 1 || quizModes.contains("All Excipients")
        val requiredTier = getRequiredTier(isMultiOrAll, qType, aType)

        return effectiveTier.ordinal >= requiredTier.ordinal
    }

    actual fun isSpecialModeUnlocked(modeId: String): Boolean {
        val requiredTier = when(modeId) {
            "lanette_lingering", "emulsion_types" -> getProgression("Creams & Emulsions").tier
            "cellulose_connoisseur" -> getProgression("Solid dosage forms").tier
            "stunning_stability" -> getProgression("Preservatives & antioxidants").tier
            else -> ProgressionTier.LOCKED
        }
        return requiredTier == ProgressionTier.FULLY_UNLOCKED
    }

    actual fun unlockTimeAttack(qType: PropertyType, aType: PropertyType) {
        settings[getTimeAttackUnlockKey(qType, aType)] = true
    }

    actual fun isTimeAttackUnlocked(qType: PropertyType, aType: PropertyType): Boolean {
        return settings.getBoolean(getTimeAttackUnlockKey(qType, aType), false)
    }

    actual fun recordSurvivalCompletion(quizMode: String, qType: PropertyType, aType: PropertyType) {
        settings[getSurvivalCompleteKey(quizMode, qType, aType)] = true
    }

    actual fun hasCompletedAllSurvivalQuizzes(quizMode: String): Boolean {
        return getMissingSurvivalPair(quizMode) == null
    }

    // New helper to identify exactly what is missing
    actual fun getMissingSurvivalPair(quizMode: String): String? {
        val categoryExcipients = if (quizMode == "All Excipients") excipients else com.example.excipientquiz.quizModes[quizMode] ?: return null
        val types = PropertyType.values()

        for (q in types) {
            for (a in types) {
                if (q == a || isPermanentlyDisabled(q, a)) continue

                val possibleInPair = categoryExcipients.count { excipient ->
                    val qVal = getProperty(excipient, q)
                    val aVal = getProperty(excipient, a)
                    (qVal.isNotBlank() && qVal != "none") && (aVal.isNotBlank() && aVal != "none")
                }

                if (possibleInPair == 0) continue

                val isDone = settings.getBoolean(getSurvivalCompleteKey(quizMode, q, a), false)
                if (!isDone) {
                    // Return the human-readable missing pair
                    return "${q.name.lowercase().replace("_", " ")} to ${a.name.lowercase().replace("_", " ")}"
                }
            }
        }
        return null // Null means everything is complete
    }

    actual fun recordSpeedrunResult(quizMode: String, qType: PropertyType, aType: PropertyType, score: Int, time: Long) {
        val scoreKey = getSpeedrunScoreKey(quizMode, qType, aType)
        val timeKey = getSpeedrunTimeKey(quizMode, qType, aType)
        
        val currentBestScore = settings.getInt(scoreKey, 0)
        val currentBestTime = settings.getLong(timeKey, Long.MAX_VALUE)
        
        if (score > currentBestScore || (score == currentBestScore && time < currentBestTime)) {
            settings[scoreKey] = score
            settings[timeKey] = time
        }
    }

    actual fun hasCompletedSpeedrunAchievement(quizMode: String): Boolean {
        val categoryExcipients = com.example.excipientquiz.quizModes[quizMode] ?: return false
        val types = PropertyType.values()
        
        var totalPossible = 0
        var totalScore = 0
        var totalTime = 0L
        
        for (q in types) {
            for (a in types) {
                if (q == a || isPermanentlyDisabled(q, a)) continue
                
                val possibleInPair = categoryExcipients.count { excipient ->
                    val qVal = getProperty(excipient, q)
                    val aVal = getProperty(excipient, a)
                    (qVal.isNotBlank() && qVal != "none") && (aVal.isNotBlank() && aVal != "none")
                }
                
                if (possibleInPair == 0) continue
                
                totalPossible += possibleInPair
                
                val score = settings.getInt(getSpeedrunScoreKey(quizMode, q, a), 0)
                val time = settings.getLong(getSpeedrunTimeKey(quizMode, q, a), 0L)
                
                totalScore += score
                totalTime += time
            }
        }
        
        if (totalPossible == 0) return false
        
        val goalScore = (totalPossible * 0.9).toInt()
        val maxTime = (totalScore * 3.5).toLong()
        
        return totalScore >= goalScore && totalTime <= maxTime && totalScore > 0
    }

    actual fun getSpeedrunRecommendation(quizMode: String): String? {
        val categoryExcipients = com.example.excipientquiz.quizModes[quizMode] ?: return null
        val types = PropertyType.values()

        var slowestPair: String? = null
        var maxTimePerQuestion = -1.0

        for (q in types) {
            for (a in types) {
                if (q == a || isPermanentlyDisabled(q, a)) continue

                val possibleInPair = categoryExcipients.count { excipient ->
                    val qVal = getProperty(excipient, q)
                    val aVal = getProperty(excipient, a)
                    (qVal.isNotBlank() && qVal != "none") && (aVal.isNotBlank() && aVal != "none")
                }

                if (possibleInPair == 0) continue

                val score = settings.getInt(getSpeedrunScoreKey(quizMode, q, a), 0)
                if (score == 0) {
                    // Priority 1: Suggest unplayed category
                    return "${q.name.lowercase().replace("_", " ")} to ${a.name.lowercase().replace("_", " ")}"
                }

                val time = settings.getLong(getSpeedrunTimeKey(quizMode, q, a), 0L)
                val timePerQuestion = time.toDouble() / score
                if (timePerQuestion > maxTimePerQuestion) {
                    maxTimePerQuestion = timePerQuestion
                    slowestPair = "${q.name.lowercase().replace("_", " ")} to ${a.name.lowercase().replace("_", " ")}"
                }
            }
        }

        // Priority 2: Suggest slowest category
        return slowestPair
    }
}
