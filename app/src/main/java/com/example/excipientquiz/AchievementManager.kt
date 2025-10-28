package com.example.excipientquiz

import android.content.Context
import android.content.SharedPreferences

object AchievementManager {

    private const val PREFS_NAME = "ExcipientQuizAchievements"

    // --- Main Achievement IDs ---
    const val TIME_ATTACK_ACE = "achievement_time_attack_ace"
    const val SURVIVALIST = "achievement_survivalist"

    // --- Dynamic Achievement ID Generators ---
    fun getSurvivalExpertId(category: String) = "expert_survival_${category.lowercase().replace(" ", "_")}"
    fun getTimeAttackExpertId(category: String) = "expert_timing_${category.lowercase().replace(" ", "_")}"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // --- Public unlock status check ---
    fun isAchievementUnlocked(context: Context, achievementId: String): Boolean {
        return getPreferences(context).getBoolean(achievementId, false)
    }

    private fun unlockAchievement(context: Context, achievementId: String) {
        getPreferences(context).edit().putBoolean(achievementId, true).apply()
    }

    // --- Game Completion Tracking ---
    private fun getCompletionKey(quizMode: String, gameMode: GameMode, qType: PropertyType, aType: PropertyType): String {
        val sortedPair = listOf(qType.name, aType.name).sorted()
        return "completed_${gameMode.name}_${quizMode}_${sortedPair[0]}_${sortedPair[1]}"
    }

    private fun markGameAsCompleted(context: Context, gameMode: GameMode, quizMode: String, qType: PropertyType, aType: PropertyType) {
        val key = getCompletionKey(quizMode, gameMode, qType, aType)
        getPreferences(context).edit().putBoolean(key, true).apply()
    }

    private fun areAllPairsCompletedForMode(context: Context, quizMode: String, gameMode: GameMode): Boolean {
        val allPropTypes = PropertyType.values()
        val allPossiblePairs = allPropTypes.flatMap { p1 ->
            allPropTypes.mapNotNull { p2 ->
                if (p1 != p2) setOf(p1, p2) else null
            }
        }.distinct()

        return allPossiblePairs.all { pair ->
            val key = getCompletionKey(quizMode, gameMode, pair.first(), pair.last())
            getPreferences(context).getBoolean(key, false)
        }
    }

    private fun areAllExpertAchievementsUnlocked(context: Context, gameMode: GameMode): Boolean {
        val allProgressionCategories = quizModes.keys.filter { it != "All Excipients" && it != "Other" }
        return allProgressionCategories.all { category ->
            val expertId = if (gameMode == GameMode.SURVIVAL) getSurvivalExpertId(category) else getTimeAttackExpertId(category)
            isAchievementUnlocked(context, expertId)
        }
    }

    fun recordCompletionAndCheckForNewAchievements(
        context: Context, gameMode: GameMode, quizModes: Set<String>, 
        qType: PropertyType, aType: PropertyType, wasSuccessful: Boolean
    ): List<Achievement> {

        if (!wasSuccessful || quizModes.size != 1) return emptyList()
        val quizMode = quizModes.first()
        if (quizMode == "All Excipients" || quizMode == "Other") return emptyList()

        markGameAsCompleted(context, gameMode, quizMode, qType, aType)

        val newlyUnlocked = mutableListOf<Achievement>()

        val expertId = if (gameMode == GameMode.SURVIVAL) getSurvivalExpertId(quizMode) else getTimeAttackExpertId(quizMode)
        if (!isAchievementUnlocked(context, expertId)) {
            if (areAllPairsCompletedForMode(context, quizMode, gameMode)) {
                unlockAchievement(context, expertId)
                val name = "$quizMode ${if (gameMode == GameMode.SURVIVAL) "Survival" else "Timing"} Expert"
                newlyUnlocked.add(
                    Achievement(
                        id = expertId, 
                        name = name, 
                        descriptionResId = R.string.achievement_expert_desc, 
                        descriptionFormatArgs = listOf(quizMode, gameMode.name.replace("_", " ")),
                        imageRes = if (gameMode == GameMode.SURVIVAL) R.drawable.ic_survival else R.drawable.ic_time
                    )
                )

                val megaId = if (gameMode == GameMode.SURVIVAL) SURVIVALIST else TIME_ATTACK_ACE
                if (!isAchievementUnlocked(context, megaId)) {
                    if (areAllExpertAchievementsUnlocked(context, gameMode)) {
                        unlockAchievement(context, megaId)
                        val megaName = if (gameMode == GameMode.SURVIVAL) "Survivalist" else "Time Attack Ace"
                        val descId = if (gameMode == GameMode.SURVIVAL) R.string.achievement_survivalist_desc else R.string.achievement_time_attack_ace_desc
                        newlyUnlocked.add(
                            Achievement(
                                id = megaId, 
                                name = megaName, 
                                descriptionResId = descId,
                                imageRes = R.drawable.badge_time_attack_ace
                            )
                        )
                    }
                }
            }
        }
        
        return newlyUnlocked
    }
}
