package com.example.excipientquiz

expect object ProgressionManager {
    fun getProgression(quizMode: String): Progression

    fun updateProgression(quizMode: String, score: Int, time: Long, wasSuccessful: Boolean): ProgressionTier

    fun getHighScore(key: String): Long

    fun setHighScore(key: String, score: Long)

    fun getHighScoreString(key: String): String

    fun setHighScoreString(key: String, value: String)

    fun isPlayable(quizModes: Set<String>, qType: PropertyType, aType: PropertyType, gameMode: GameMode): Boolean

    fun isPermanentlyDisabled(qType: PropertyType, aType: PropertyType): Boolean

    fun isSpecialModeUnlocked(modeId: String): Boolean

    fun unlockTimeAttack(qType: PropertyType, aType: PropertyType)

    fun isTimeAttackUnlocked(qType: PropertyType, aType: PropertyType): Boolean

    fun recordSurvivalCompletion(quizMode: String, qType: PropertyType, aType: PropertyType)

    fun hasCompletedAllSurvivalQuizzes(quizMode: String): Boolean

    fun getMissingSurvivalPair(quizMode: String): String?

    fun recordSpeedrunResult(quizMode: String, qType: PropertyType, aType: PropertyType, score: Int, time: Long)

    fun hasCompletedSpeedrunAchievement(quizMode: String): Boolean

    fun getSpeedrunRecommendation(quizMode: String): String?
}
