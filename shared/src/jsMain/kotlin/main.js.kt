// In C:/Users/Tom/AndroidStudioProjects/ExcipientQuiz/shared/src/jsMain/kotlin/main.js.kt
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.excipientquiz.App
import com.example.excipientquiz.SettingsManager
import com.example.excipientquiz.resetAllUserData
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    // --- TEMPORARY DEBUGGING ---
    // Check if the URL is like "http://localhost:8080/?reset=true"
    val forceReset = window.location.search.contains("reset=true")
    if (forceReset) {
        // Nuke all saved data from this origin.
        resetAllUserData()
        console.log("DEBUG: Forced reset executed. All user data cleared.")
    }

    // Read the language setting *after* a potential reset.
    var language = SettingsManager.getLanguage()

    // --- DEBUG LOGGING ---
    console.log("DEBUG: SettingsManager.getLanguage() returned: '$language'")

    // This logic is correct, but only runs if the saved setting is blank.
    if (language.isBlank() || forceReset) {
        val supportedLanguages = listOf("en", "nl", "fr", "de")
        val browserLanguage = window.navigator.language.take(2)
        language = if (browserLanguage in supportedLanguages && !forceReset) {
            browserLanguage
        } else {
            "en" // Default to English, or force English on reset
        }
        console.log("DEBUG: No saved language found. Determined language: '$language'")
    }

    // Save the determined language. App() will read this.
    SettingsManager.setLanguage(language)
    console.log("DEBUG: Final language being set: '$language'")

    onWasmReady {
        @OptIn(ExperimentalComposeUiApi::class)
        ComposeViewport(document.body!!) {
            App()
        }
    }
}
