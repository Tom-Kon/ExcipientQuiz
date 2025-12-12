// In shared/src/commonMain/kotlin/com/example/excipientquiz/LocaleProvider.kt

package com.example.excipientquiz

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.intl.Locale

/**
 * Provides a CompositionLocal for the current app Locale.
 * Any composable can access the current locale via `LocalLocale.current`.
 * We provide a default value of "en" to prevent crashes in previews or tests.
 */
val LocalLocale = compositionLocalOf { Locale("en") }
    