package com.example.excipientquiz

actual object AchievementManager {
    actual fun isAchievementUnlocked(achievementId: String): Boolean {
        return Achievement.valueOf(achievementId).isUnlocked()
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
