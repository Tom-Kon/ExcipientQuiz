package com.example.excipientquiz

expect object AchievementManager {
    fun isAchievementUnlocked(achievementId: String): Boolean

    fun recordCompletionAndCheckForNewAchievements(
        gameMode: GameMode,
        quizModes: Set<String>,
        questionType: PropertyType,
        answerType: PropertyType,
        wasSuccessful: Boolean,
        score: Int,
        time: Long,
        questionCount: Int
    ): List<Achievement>

    fun getAllAchievements(): List<Achievement>
}
