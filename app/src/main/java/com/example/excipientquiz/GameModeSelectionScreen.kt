package com.example.excipientquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameModeSelectionScreen(
    questionType: PropertyType,
    answerType: PropertyType,
    quizModes: Set<String>,
    onModeSelected: (GameMode) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val isTimeAttackUnlocked = ProgressionManager.isPlayable(context, quizModes, questionType, answerType, GameMode.TIME_ATTACK)
    val isSurvivalUnlocked = ProgressionManager.isPlayable(context, quizModes, questionType, answerType, GameMode.SURVIVAL)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Choose Your Mode", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(48.dp))

        ModeSelectionCard(
            title = "Time Attack",
            iconRes = R.drawable.ic_time,
            isUnlocked = isTimeAttackUnlocked,
            onClick = { if (isTimeAttackUnlocked) onModeSelected(GameMode.TIME_ATTACK) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        ModeSelectionCard(
            title = "Survival",
            iconRes = R.drawable.ic_survival,
            isUnlocked = isSurvivalUnlocked,
            onClick = { if (isSurvivalUnlocked) onModeSelected(GameMode.SURVIVAL) }
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(onClick = onBack) {
            Text("Back to Main Menu")
        }
    }
}

@Composable
private fun ModeSelectionCard(title: String, iconRes: Int, isUnlocked: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable(onClick = onClick)
            .alpha(if (isUnlocked) 1f else 0.5f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isUnlocked) {
                Icon(Icons.Default.Lock, contentDescription = "Locked", modifier = Modifier.size(48.dp))
                Spacer(modifier = Modifier.height(8.dp))
            }
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
