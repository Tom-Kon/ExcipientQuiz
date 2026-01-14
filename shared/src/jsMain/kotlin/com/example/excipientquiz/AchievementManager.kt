package com.example.excipientquiz

actual object AchievementManager {

    actual fun isAchievementUnlocked(achievementId: String): Boolean {
        return Achievement.values().find { it.name == achievementId }?.isUnlocked() ?: false
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
        // Discount invalid/unplayable pairs
        if (ProgressionManager.isPermanentlyDisabled(questionType, answerType) || questionType == answerType) {
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

        // Filter for achievements that AREN'T unlocked yet but DO meet the criteria now
        val newlyUnlocked = Achievement.values().filter { ach ->
            !ach.isUnlocked() && ach.check(score, time, wasSuccessful, questionCount, gameMode, questionType, answerType, quizModes)
        }
        
        newlyUnlocked.forEach { it.unlock() }
        
        return newlyUnlocked
    }

    actual fun getAllAchievements(): List<Achievement> {
        return Achievement.values().toList()
    }
}
