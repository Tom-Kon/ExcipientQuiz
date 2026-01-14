package com.example.excipientquiz

import android.content.Context

actual object AchievementManager {

    private const val PREFS_NAME = "ExcipientQuizAchievements"

    private fun getPreferences() = AppContext.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    actual fun isAchievementUnlocked(achievementId: String): Boolean {
        return getPreferences().getBoolean(achievementId, false)
    }

    actual fun recordCompletionAndCheckForNewAchievements(
        gameMode: GameMode,
        quizModes: Set<String>,
        questionType: PropertyType,
        answerType: PropertyType,
        wasSuccessful: Boolean,
        score: Int,
        time: Long,
        questionCount: Int
    ): List<Achievement> {
        if (ProgressionManager.isPermanentlyDisabled(questionType, answerType)) {
            return emptyList()
        }

        // Record the results before checking achievements
        if (wasSuccessful) {
            quizModes.forEach { mode ->
                if (gameMode == GameMode.SURVIVAL) {
                    ProgressionManager.recordSurvivalCompletion(mode, questionType, answerType)
                } else if (gameMode == GameMode.EXCIPIENT_SPEEDRUN) {
                    ProgressionManager.recordSpeedrunResult(mode, questionType, answerType, score, time)
                }
            }
        }

        val unlocked = Achievement.values().filter { ach ->
            !ach.isUnlocked() && ach.check(score, time, wasSuccessful, questionCount, gameMode, questionType, answerType, quizModes)
        }
        if (unlocked.isNotEmpty()) {
            unlocked.forEach { it.unlock() }
        }
        return unlocked
    }

    actual fun getAllAchievements(): List<Achievement> {
        return Achievement.values().toList()
    }
}
