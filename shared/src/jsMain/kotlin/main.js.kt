import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.example.excipientquiz.App
import com.example.excipientquiz.SettingsManager
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // 1. Determine the language before anything starts.
    // This ensures that when App() is called, SettingsManager.getLanguage()
    // returns the correct, persisted user choice.
    val savedLanguage = window.localStorage.getItem("language_pref")
    
    // If no language is saved, detect browser language
    if (savedLanguage == null) {
        val browserLang = window.navigator.language.take(2).lowercase()
        val supported = listOf("en", "nl", "fr", "de")
        val initialLang = if (browserLang in supported) browserLang else "en"
        window.localStorage.setItem("language_pref", initialLang)
    }

    onWasmReady {
        val body = document.body ?: return@onWasmReady
        ComposeViewport(body) {
            App()
        }
    }
}
