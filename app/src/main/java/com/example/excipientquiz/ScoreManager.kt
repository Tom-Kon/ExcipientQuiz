package com.example.excipientquiz

import android.content.Context
import android.content.SharedPreferences

object ScoreManager {

    private const val PREFS_NAME = "ExcipientQuizPrefs"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // --- Key Generation --- //
    private fun getQuizModesKey(quizModes: Set<String>): String {
        // Creates a stable, alphabetical key from the set of modes.
        return quizModes.toSortedSet().joinToString("_")
    }

    private fun getScoreKey(gameMode: GameMode, qType: PropertyType, aType: PropertyType, quizModes: Set<String>) = 
        "${gameMode.name}_${qType.name}_${aType.name}_${getQuizModesKey(quizModes)}_score"

    private fun getTimeKey(gameMode: GameMode, qType: PropertyType, aType: PropertyType, quizModes: Set<String>) = 
        "${gameMode.name}_${qType.name}_${aType.name}_${getQuizModesKey(quizModes)}_time"


    // --- Time Attack High Score --- //

    fun getTimeAttackHighScore(context: Context, qType: PropertyType, aType: PropertyType, quizModes: Set<String>): Pair<Int, Long> {
        val prefs = getPreferences(context)
        val score = prefs.getInt(getScoreKey(GameMode.TIME_ATTACK, qType, aType, quizModes), 0)
        val time = prefs.getLong(getTimeKey(GameMode.TIME_ATTACK, qType, aType, quizModes), 0L)
        return Pair(score, time)
    }

    fun saveTimeAttackHighScore(context: Context, qType: PropertyType, aType: PropertyType, quizModes: Set<String>, score: Int, time: Long) {
        val currentHighScore = getTimeAttackHighScore(context, qType, aType, quizModes)
        if (score > currentHighScore.first || (score == currentHighScore.first && time < currentHighScore.second)) {
            val editor = getPreferences(context).edit()
            editor.putInt(getScoreKey(GameMode.TIME_ATTACK, qType, aType, quizModes), score)
            editor.putLong(getTimeKey(GameMode.TIME_ATTACK, qType, aType, quizModes), time)
            editor.apply()
        }
    }

    // --- Survival High Score --- //

    fun getSurvivalHighScore(context: Context, qType: PropertyType, aType: PropertyType, quizModes: Set<String>): Int {
        return getPreferences(context).getInt(getScoreKey(GameMode.SURVIVAL, qType, aType, quizModes), 0)
    }

    fun saveSurvivalHighScore(context: Context, qType: PropertyType, aType: PropertyType, quizModes: Set<String>, score: Int) {
        if (score > getSurvivalHighScore(context, qType, aType, quizModes)) {
            val editor = getPreferences(context).edit()
            editor.putInt(getScoreKey(GameMode.SURVIVAL, qType, aType, quizModes), score)
            editor.apply()
        }
    }
}
