package com.example.excipientquiz

// Inside LocaleProvider.kt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.intl.Locale

object LocaleProvider {
    val current: Locale
        @Composable
        @ReadOnlyComposable
        get() = LocalAppLanguage.current // Use the new unique name here
}
