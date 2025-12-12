package com.example.excipientquiz

import android.app.Activity
import android.content.Context
import java.util.Locale

actual fun getPlatformName(): String = "Android"


/**
 * This function's ONLY responsibility is to change the Android system's locale
 * and then trigger an Activity recreation to apply it.
 */
actual fun setLocale(languageCode: String) {
    val context = AppContext.context
    val activity = context as? Activity

    // If there is no activity, we can't do anything, so we stop here.
    if (activity == null) return

    val locale = Locale(languageCode)

    // 1. Apply the new locale to the Android system configuration.
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    @Suppress("DEPRECATION") // This is the correct way for older APIs
    resources.updateConfiguration(configuration, resources.displayMetrics)

    // 2. Recreate the activity to apply the new resources.
    // The UI will be rebuilt from scratch with the new language.
    activity.recreate()
}


actual fun resetAllUserData() {
    val context = AppContext.context
    // This looks for any file starting with your app's package name in the shared_prefs folder.
    val sharedPrefsFolder = java.io.File(context.applicationInfo.dataDir, "shared_prefs")
    sharedPrefsFolder.listFiles { _, name -> name.startsWith("com.example.excipientquiz") }?.forEach { it.delete() }

    // Also clear the multiplatform-settings file specifically
    context.getSharedPreferences("com.russhwolf.settings.SETTINGS", Context.MODE_PRIVATE).edit().clear().apply()
}
