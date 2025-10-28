package com.example.excipientquiz

import android.content.Context
import android.content.SharedPreferences

object SettingsManager {

    private const val PREFS_NAME = "ExcipientQuizSettings"
    private const val KEY_MUSIC_ENABLED = "music_enabled"
    private const val KEY_SFX_ENABLED = "sfx_enabled"
    private const val KEY_LANGUAGE = "language"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isMusicEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_MUSIC_ENABLED, true)
    }

    fun setMusicEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_MUSIC_ENABLED, enabled).apply()
    }

    fun isSfxEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_SFX_ENABLED, true)
    }

    fun setSfxEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_SFX_ENABLED, enabled).apply()
    }

    fun getLanguage(context: Context): String {
        return getPreferences(context).getString(KEY_LANGUAGE, "en") ?: "en"
    }

    fun setLanguage(context: Context, languageCode: String) {
        getPreferences(context).edit().putString(KEY_LANGUAGE, languageCode).apply()
    }
}
