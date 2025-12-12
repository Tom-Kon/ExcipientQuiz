package com.example.excipientquiz

expect object ProgressionManager {
    fun getProgression(quizMode: String): Progression

    fun updateProgression(quizMode: String, score: Int, time: Long, wasSuccessful: Boolean): ProgressionTier

    fun getHighScore(key: String): Long

    fun setHighScore(key: String, score: Long)

    fun getHighScoreString(key: String): String

    fun setHighScoreString(key: String, value: String)

    fun isPlayable(quizModes: Set<String>, qType: PropertyType, aType: PropertyType, gameMode: GameMode): Boolean

    fun isSpecialModeUnlocked(modeId: String): Boolean

    fun unlockTimeAttack(qType: PropertyType, aType: PropertyType)

    fun isTimeAttackUnlocked(qType: PropertyType, aType: PropertyType): Boolean
}
