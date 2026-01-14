package com.example.excipientquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.excipientquiz.ui.theme.ExcipientQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Initialize AppContext with this activity reference
        AppContext.initialize(this)
        
        // 2. Get the saved language and apply it to the Android configuration
        // This ensures that string resources loaded by Compose use the correct locale.
        val savedLanguage = SettingsManager.getLanguage()
        LanguageManager.applyLanguageInternal(savedLanguage)
        
        setContent {
            ExcipientQuizTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExcipientQuizTheme {
        App()
    }
}
