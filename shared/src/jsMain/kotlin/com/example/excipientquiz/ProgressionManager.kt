package com.example.excipientquiz

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

actual object ProgressionManager {

    private val settings: Settings by lazy { createSettings() }

    private fun getTierKey(quizMode: String) = "tier_$quizMode"
    private fun getTimeAttackUnlockKey(qType: PropertyType, aType: PropertyType) = "time_attack_${qType}_$aType"

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

    actual fun isPlayable(quizModes: Set<String>, qType: PropertyType, aType: PropertyType, gameMode: GameMode): Boolean {
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
}
