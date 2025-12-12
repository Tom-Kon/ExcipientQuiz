package com.example.excipientquiz

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

actual object SettingsManager {

    private const val PREFS_NAME = "ExcipientQuizSettings"
    private const val KEY_MUSIC_ENABLED = "music_enabled"
    private const val KEY_SFX_ENABLED = "sfx_enabled"
    private const val KEY_LANGUAGE = "language"

    private fun getPreferences(): SharedPreferences {
        return AppContext.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    actual fun isMusicEnabled(): Boolean {
        return getPreferences().getBoolean(KEY_MUSIC_ENABLED, true)
    }

    actual fun setMusicEnabled(enabled: Boolean) {
        getPreferences().edit { putBoolean(KEY_MUSIC_ENABLED, enabled) }
    }

    actual fun isSfxEnabled(): Boolean {
        return getPreferences().getBoolean(KEY_SFX_ENABLED, true)
    }

    actual fun setSfxEnabled(enabled: Boolean) {
        getPreferences().edit { putBoolean(KEY_SFX_ENABLED, enabled) }
    }

    actual fun getLanguage(): String {
        return getPreferences().getString(KEY_LANGUAGE, "en") ?: "en"
    }

    actual fun setLanguage(language: String) {
        getPreferences().edit { putString(KEY_LANGUAGE, language) }
    }
}
