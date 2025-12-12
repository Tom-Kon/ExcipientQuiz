package com.example.excipientquiz

import android.content.Context

object ScoreManager {
    private const val PREFS_NAME = "ExcipientQuizScores"

    private fun getPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // --- Excipient Speedrun --- //
    fun saveTimeAttackHighScore(context: Context, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>, score: Int, time: Long) {
        val key = "excipient_speedrun_hs_${questionType}_${answerType}_${quizModes.joinToString("_")}"
        getPrefs(context).edit().putInt(key, score).putLong("${key}_time", time).apply()
    }

    fun getTimeAttackHighScore(context: Context, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>): Pair<Int, Long> {
        val key = "excipient_speedrun_hs_${questionType}_${answerType}_${quizModes.joinToString("_")}"
        val score = getPrefs(context).getInt(key, 0)
        val time = getPrefs(context).getLong("${key}_time", 0L)
        return score to time
    }

    // --- Survival --- //
    fun saveSurvivalHighScore(context: Context, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>, score: Int) {
        val key = "survival_hs_${questionType}_${answerType}_${quizModes.joinToString("_")}"
        getPrefs(context).edit().putInt(key, score).apply()
    }

    fun getSurvivalHighScore(context: Context, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>): Int {
        val key = "survival_hs_${questionType}_${answerType}_${quizModes.joinToString("_")}"
        return getPrefs(context).getInt(key, 0)
    }
    
    // --- Special Game Modes --- //
    fun saveSpecialModeHighScore(context: Context, modeId: String, score: Int) {
        val key = "special_hs_$modeId"
        getPrefs(context).edit().putInt(key, score).apply()
    }

    fun getSpecialModeHighScore(context: Context, modeId: String): Int {
        val key = "special_hs_$modeId"
        return getPrefs(context).getInt(key, 0)
    }
}
