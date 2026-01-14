package com.example.excipientquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.gamemode_button_back
import excipientquiz.shared.generated.resources.gamemode_study
import excipientquiz.shared.generated.resources.gamemode_study_desc
import excipientquiz.shared.generated.resources.gamemode_survival
import excipientquiz.shared.generated.resources.gamemode_survival_desc
import excipientquiz.shared.generated.resources.gamemode_time_attack
import excipientquiz.shared.generated.resources.gamemode_time_attack_desc
import excipientquiz.shared.generated.resources.gamemode_title
import excipientquiz.shared.generated.resources.ic_study
import excipientquiz.shared.generated.resources.ic_survival
import excipientquiz.shared.generated.resources.ic_time
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GameModeSelectionScreen(
    questionType: PropertyType,
    answerType: PropertyType,
    quizModes: Set<String>,
    onModeSelected: (GameMode) -> Unit,
    onBack: () -> Unit
) {
    val isExcipientSpeedrunUnlocked = ProgressionManager.isPlayable(quizModes, questionType, answerType, GameMode.EXCIPIENT_SPEEDRUN)
    val isSurvivalUnlocked = ProgressionManager.isPlayable(quizModes, questionType, answerType, GameMode.SURVIVAL)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(Res.string.gamemode_title), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        ModeSelectionCard(
            title = stringResource(Res.string.gamemode_time_attack),
            description = stringResource(Res.string.gamemode_time_attack_desc),
            iconRes = "ic_time",
            isUnlocked = isExcipientSpeedrunUnlocked,
            onClick = { if (isExcipientSpeedrunUnlocked) onModeSelected(GameMode.EXCIPIENT_SPEEDRUN) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ModeSelectionCard(
            title = stringResource(Res.string.gamemode_survival),
            description = stringResource(Res.string.gamemode_survival_desc),
            iconRes = "ic_survival",
            isUnlocked = isSurvivalUnlocked,
            onClick = { if (isSurvivalUnlocked) onModeSelected(GameMode.SURVIVAL) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        ModeSelectionCard(
            title = stringResource(Res.string.gamemode_study),
            description = stringResource(Res.string.gamemode_study_desc),
            iconRes = "ic_study",
            isUnlocked = true,
            onClick = { onModeSelected(GameMode.STUDY) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text(stringResource(Res.string.gamemode_button_back))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ModeSelectionCard(
    title: String,
    description: String,
    iconRes: String,
    isUnlocked: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick, enabled = isUnlocked)
            .alpha(if (isUnlocked) 1f else 0.5f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(48.dp)) {
                if (!isUnlocked) {
                    Icon(Icons.Default.Lock, contentDescription = "Locked", modifier = Modifier.size(32.dp))
                } else {
                    getGameModeDrawableResourceByName(iconRes)?.let {
                        Image(painter = painterResource(it), contentDescription = title, modifier = Modifier.size(40.dp))
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = description, fontSize = 12.sp, style = MaterialTheme.typography.bodySmall, lineHeight = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
private fun getGameModeDrawableResourceByName(name: String): DrawableResource? {
    return when (name) {
        "ic_time" -> Res.drawable.ic_time
        "ic_survival" -> Res.drawable.ic_survival
        "ic_study" -> Res.drawable.ic_study
        else -> null
    }
}
