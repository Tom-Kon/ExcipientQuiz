package com.example.excipientquiz

actual object AchievementManager {
    actual fun isAchievementUnlocked(achievementId: String): Boolean {
        // Implement logic to check if an achievement is unlocked in JS
        return false
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
        // Implement achievement checking logic for JS
        return emptyList()
    }

    actual fun getAllAchievements(): List<Achievement> {
        return Achievement.values().toList()
    }
}
