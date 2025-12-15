package com.example.excipientquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TutorialScreen(onComplete: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome to the Pharmaceutical Excipient Quiz!",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your goal in this game is to master pharmaceutical excipients. This includes their names and alternative names, their structures, their functions, and even more!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Many game modes start out by being locked at first. Unlock game modes by completing quizzes in survival mode! You can select what excipients you want to play the quiz with via the quiz content button. When you complete individual categories, you can combine them and play with more excipients at once! If you forgot what is unlocked and what is not, check out the progression button.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Once you\'re comfortable with survival and have unlocked the Excipient Speedrun mode, test your speed to set new high scores!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Be sure to check out the achievements you can earn in the achievements tab. Furthermore, the encyclopedia contains a lot of additional information about all of the excipients in the game.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onComplete) {
            Text("Let\'s Go!")
        }
    }
}
