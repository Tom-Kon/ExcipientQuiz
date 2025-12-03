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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpecialModeResultScreen(
    modeId: String,
    score: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var isNewHighScore by remember { mutableStateOf(false) }
    var previousHighScoreString by remember { mutableStateOf("none") }

    LaunchedEffect(Unit) {
        val oldHighScore = ScoreManager.getSpecialModeHighScore(context, modeId)
        if (oldHighScore > 0) {
            previousHighScoreString = "$oldHighScore"
        }
        if (score > oldHighScore) {
            isNewHighScore = true
            ScoreManager.saveSpecialModeHighScore(context, modeId, score)
            SoundManager.playSound(context, R.raw.succesend)
        } else {
            SoundManager.playSound(context, R.raw.gameover)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isNewHighScore) {
            Text(
                stringResource(id = R.string.resultscreen_new_highscore),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(stringResource(id = R.string.resultscreen_score_survival, score), fontSize = 30.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(stringResource(id = R.string.resultscreen_previous_highscore, previousHighScoreString), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onBack) { Text(stringResource(id = R.string.common_back)) }
    }
}
