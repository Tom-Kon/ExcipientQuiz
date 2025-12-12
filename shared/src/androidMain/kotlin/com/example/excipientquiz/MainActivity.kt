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
        AppContext.initialize(this)
        setContent {
            ExcipientQuizTheme {
                // Call App() instead of AppContent()
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExcipientQuizTheme {
        // Call App() instead of AppContent() for the preview as well
        App()
    }
}
