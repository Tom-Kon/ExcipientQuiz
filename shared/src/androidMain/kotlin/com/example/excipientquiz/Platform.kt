package com.example.excipientquiz

import android.app.Activity
import android.content.Context
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.settings_language_de
import excipientquiz.shared.generated.resources.settings_language_en
import excipientquiz.shared.generated.resources.settings_language_fr
import excipientquiz.shared.generated.resources.settings_language_nl
import java.util.Locale

actual fun getPlatformName(): String = "Android"

actual fun setLocale(languageCode: String) {
    LanguageManager.applyLanguage(languageCode)
}

actual object LanguageManager {
    private val languages = listOf(
        LanguageOption("en", Res.string.settings_language_en, "🇬🇧"),
        LanguageOption("nl", Res.string.settings_language_nl, "🇳🇱"),
        LanguageOption("fr", Res.string.settings_language_fr, "🇫🇷"),
        LanguageOption("de", Res.string.settings_language_de, "🇩🇪")
    )

    actual fun getLanguages(): List<LanguageOption> = languages

    actual fun getCurrentLanguageCode(): String {
        return SettingsManager.getLanguage()
    }

    /**
     * Public actual function for changing language at runtime (includes recreate)
     */
    actual fun applyLanguage(languageCode: String) {
        applyLanguageInternal(languageCode)
        AppContext.activity?.recreate()
    }

    /**
     * Internal helper to apply locale to configuration WITHOUT triggering recreate.
     * Useful during Activity.onCreate.
     */
    fun applyLanguageInternal(languageCode: String) {
        val context = AppContext.activity ?: AppContext.context
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        
        @Suppress("DEPRECATION")
        resources.updateConfiguration(configuration, resources.displayMetrics)
        
        SettingsManager.setLanguage(languageCode)
    }
}

actual fun resetAllUserData() {
    val context = AppContext.context
    // This looks for any file starting with your app's package name in the shared_prefs folder.
    val sharedPrefsFolder = java.io.File(context.applicationInfo.dataDir, "shared_prefs")
    sharedPrefsFolder.listFiles { _, name -> name.startsWith("ExcipientQuizSettings") }?.forEach { it.delete() }

    // Also clear the multiplatform-settings file specifically
    context.getSharedPreferences("com.russhwolf.settings.SETTINGS", Context.MODE_PRIVATE).edit().clear().apply()
}
