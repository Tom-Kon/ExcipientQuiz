package com.example.excipientquiz

import androidx.compose.runtime.Composable

// Common declaration, visible in commonMain
@Composable
expect fun localizedStringRuntime(resId: Int, localeCode: String = LocalLocale.current.language): String
