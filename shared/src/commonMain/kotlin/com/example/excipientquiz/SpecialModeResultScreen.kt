package com.example.excipientquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpecialModeResultScreen(
    modeId: String,
    score: Int,
    onBack: () -> Unit
) {
    var isNewHighScore by remember { mutableStateOf(false) } // Placeholder
    var previousHighScoreString by remember { mutableStateOf("none") } // Placeholder

    // High score and sound logic will be re-implemented

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isNewHighScore) {
            Text(
                "New high score!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text("You got $score correct!", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Previous high score: $previousHighScoreString", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}
