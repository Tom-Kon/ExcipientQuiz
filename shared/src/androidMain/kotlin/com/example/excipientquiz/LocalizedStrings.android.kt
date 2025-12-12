package com.example.excipientquiz

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale as JavaLocale

@Composable
actual fun localizedStringRuntime(resId: Int, localeCode: String): String {
    val context = LocalContext.current
    return remember(localeCode, resId) {
        val config = context.resources.configuration
        val originalLocale = config.locales.get(0) ?: config.locale
        try {
            config.setLocale(JavaLocale(localeCode))
            val localizedContext = context.createConfigurationContext(config)
            localizedContext.resources.getString(resId)
        } catch (e: Exception) {
            context.getString(resId)
        } finally {
            config.setLocale(originalLocale)
        }
    }
}
