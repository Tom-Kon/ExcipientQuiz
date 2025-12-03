package com.example.excipientquiz

import android.content.Context

object AchievementManager {
    private const val PREFS_NAME = "ExcipientQuizAchievements"

    const val TIME_ATTACK_ACE = "excipient_speedrun_ace"
    const val SURVIVALIST = "survivalist"

    fun getSurvivalExpertId(category: String) = "survival_expert_$category"
    fun getTimeAttackExpertId(category: String) = "excipient_speedrun_expert_$category"

    private fun getPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isAchievementUnlocked(context: Context, achievementId: String): Boolean {
        return getPrefs(context).getBoolean(achievementId, false)
    }

    private fun unlockAchievement(context: Context, achievementId: String) {
        getPrefs(context).edit().putBoolean(achievementId, true).apply()
    }

    fun recordCompletionAndCheckForNewAchievements(
        context: Context,
        gameMode: GameMode,
        quizModes: Set<String>,
        questionType: PropertyType,
        answerType: PropertyType,
        wasSuccessful: Boolean
    ): List<Achievement> {
        if (!wasSuccessful) return emptyList()

        val newAchievements = mutableListOf<Achievement>()

        // Individual Category Achievement
        if (quizModes.size == 1) {
            val category = quizModes.first()
            if (category != "All Excipients" && category != "Other") {
                val achievementId = when (gameMode) {
                    GameMode.SURVIVAL -> getSurvivalExpertId(category)
                    GameMode.EXCIPIENT_SPEEDRUN -> getTimeAttackExpertId(category)
                }
                if (!isAchievementUnlocked(context, achievementId)) {
                    unlockAchievement(context, achievementId)
                    // We need to create the achievement object to show it
                    val achievement = allAchievements(context).find { it.id == achievementId }
                    if (achievement != null) {
                        newAchievements.add(achievement)
                    }
                }
            }
        }

        // Check for "Ace" achievements
        val allCategories = com.example.excipientquiz.quizModes.keys.filter { it != "All Excipients" && it != "Other" }

        if (gameMode == GameMode.SURVIVAL) {
            if (!isAchievementUnlocked(context, SURVIVALIST)) {
                val allSurvivalUnlocked = allCategories.all { isAchievementUnlocked(context, getSurvivalExpertId(it)) }
                if (allSurvivalUnlocked) {
                    unlockAchievement(context, SURVIVALIST)
                    val achievement = allAchievements(context).find { it.id == SURVIVALIST }
                    if (achievement != null) newAchievements.add(achievement)
                }
            }
        } else if (gameMode == GameMode.EXCIPIENT_SPEEDRUN) {
            if (!isAchievementUnlocked(context, TIME_ATTACK_ACE)) {
                val allTimeAttackUnlocked = allCategories.all { isAchievementUnlocked(context, getTimeAttackExpertId(it)) }
                if (allTimeAttackUnlocked) {
                    unlockAchievement(context, TIME_ATTACK_ACE)
                    val achievement = allAchievements(context).find { it.id == TIME_ATTACK_ACE }
                    if (achievement != null) newAchievements.add(achievement)
                }
            }
        }
        
        return newAchievements
    }

    // Helper to get all possible achievements for checking
    private fun allAchievements(context: Context): List<Achievement> {
         val staticAchievements = listOf(
            Achievement(
                id = TIME_ATTACK_ACE,
                name = "Excipient Speedrun Ace",
                descriptionResId = R.string.achievement_time_attack_ace_desc,
                imageRes = R.drawable.badge_time_attack_ace
            ),
            Achievement(
                id = SURVIVALIST,
                name = "Survivalist",
                descriptionResId = R.string.achievement_survivalist_desc,
                imageRes = R.drawable.badge_survivalist
            )
        )

        val dynamicAchievements = quizModes.keys
            .filter { it != "All Excipients" && it != "Other" }
            .flatMap { category ->
                listOf(
                    Achievement(
                        id = getSurvivalExpertId(category),
                        name = "$category Survival Expert",
                        descriptionResId = R.string.achievement_expert_desc,
                        descriptionFormatArgs = listOf(category, "Survival"),
                        imageRes = R.drawable.ic_survival // Placeholder, real image is in AchievementsScreen
                    ),
                    Achievement(
                        id = getTimeAttackExpertId(category),
                        name = "$category Timing Expert",
                        descriptionResId = R.string.achievement_expert_desc,
                        descriptionFormatArgs = listOf(category, "Excipient Speedrun"),
                        imageRes = R.drawable.ic_time // Placeholder
                    )
                )
            }
        return staticAchievements + dynamicAchievements
    }
}
