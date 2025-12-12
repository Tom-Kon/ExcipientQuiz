// In C:/Users/Tom/AndroidStudioProjects/ExcipientQuiz/shared/src/commonMain/kotlin/com/example/excipientquiz/LocaleHelpers.kt

package com.example.excipientquiz

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * A helper function to get a localized string.
 *
 * It directly uses the `stringResource` function from Compose. This function
 * is designed to automatically react to changes in the `LocalLocale`
 * CompositionLocal that is provided at the root of the app.
 */
@Composable
fun localizedString(res: StringResource): String {
    // This is all that is needed.
    // The Compose framework will see that `stringResource` depends on the
    // `LocalLocale` provider and will automatically recompose this call
    // whenever the locale changes in `App.kt`.
    return stringResource(res)
}
