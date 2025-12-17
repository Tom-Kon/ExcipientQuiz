package com.example.excipientquiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.ic_settings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalAnimationApi::class, ExperimentalResourceApi::class)
@Composable
fun ExcipientGameStartScreen(
    modifier: Modifier = Modifier,
    selectedQuizModes: Set<String>,
    questionType: PropertyType,
    answerType: PropertyType,
    onQuestionTypeChange: (PropertyType) -> Unit,
    onAnswerTypeChange: (PropertyType) -> Unit,
    onStartChallenge: () -> Unit,
    onShowOptions: () -> Unit,
    onShowAchievements: () -> Unit,
    onShowEncyclopedia: () -> Unit,
    onShowProgression: () -> Unit,
    onShowSettings: () -> Unit
) {
    val propertyTypes = PropertyType.values()

    val isPermanentlyDisabled = ProgressionManager.isPermanentlyDisabled(questionType, answerType)
    val isPairValid = questionType != answerType && !isPermanentlyDisabled
    val isPlayableSurvival = ProgressionManager.isPlayable(selectedQuizModes, questionType, answerType, GameMode.SURVIVAL)
    val isPlayableTimeAttack = ProgressionManager.isPlayable(selectedQuizModes, questionType, answerType, GameMode.EXCIPIENT_SPEEDRUN)

    fun updateQuestionType(offset: Int) {
        val newIndex = (propertyTypes.indexOf(questionType) + offset + propertyTypes.size) % propertyTypes.size
        onQuestionTypeChange(propertyTypes[newIndex])
    }

    fun updateAnswerType(offset: Int) {
        val newIndex = (propertyTypes.indexOf(answerType) + offset + propertyTypes.size) % propertyTypes.size
        onAnswerTypeChange(propertyTypes[newIndex])
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HighScore(questionType, answerType, selectedQuizModes, isPermanentlyDisabled)
            IconButton(onClick = onShowSettings) {
                Icon(painterResource(Res.drawable.ic_settings), "Settings")
            }
        }

        Text(
            text = "Pharmaceutical Excipient Quiz",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text("QUESTION", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        PropertySelector(property = questionType, onPrevious = { updateQuestionType(-1) }, onNext = { updateQuestionType(1) }, isLocked = !isPlayableSurvival && !isPlayableTimeAttack)

        Spacer(modifier = Modifier.height(32.dp))

        Text("ANSWER", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        PropertySelector(property = answerType, onPrevious = { updateAnswerType(-1) }, onNext = { updateAnswerType(1) }, isLocked = !isPlayableSurvival && !isPlayableTimeAttack)

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onStartChallenge,
            enabled = (isPlayableSurvival || isPlayableTimeAttack) && isPairValid
        ) {
            Text("Start Challenge", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(56.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = onShowOptions) {
                    Text("Quiz Content")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onShowProgression) {
                    Text("Progression")
                }
            }
            Text(
                text = "Active: ${selectedQuizModes.joinToString()}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 25.dp)) {
                Button(onClick = onShowAchievements) {
                    Text("Achievements")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onShowEncyclopedia) {
                    Text("Encyclopedia")
                }
            }
        }
    }
}

@Composable
fun HighScore(questionType: PropertyType, answerType: PropertyType, selectedQuizModes: Set<String>, isPermanentlyDisabled: Boolean) {
    if (isPermanentlyDisabled) {
        Text("This game mode is not playable", style = MaterialTheme.typography.bodyMedium)
        return
    }

    val quizModeKey = if (selectedQuizModes.size > 1) "all" else selectedQuizModes.first()
    val survivalHighScore = ProgressionManager.getHighScore("highscore_SURVIVAL_${quizModeKey}_${questionType}_$answerType")
    val timeAttackHighScoreString = ProgressionManager.getHighScoreString("highscore_EXCIPIENT_SPEEDRUN_${quizModeKey}_${questionType}_$answerType")

    Column {
        if (survivalHighScore > 0) {
            Text("Survival Record: $survivalHighScore", modifier = Modifier.padding(bottom = 4.dp))
        }
        if (timeAttackHighScoreString.isNotBlank()) {
            val parts = timeAttackHighScoreString.split("/")
            val score = parts.getOrNull(0)?.toIntOrNull() ?: 0
            val time = parts.getOrNull(1)?.toLongOrNull() ?: 0
            Text("Excipient speedrun Record: $score in ${formatTime(time)}")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PropertySelector(property: PropertyType, onPrevious: () -> Unit, onNext: () -> Unit, isLocked: Boolean) {
    var direction by remember { mutableStateOf(1) }
    var dragAmount by remember { mutableStateOf(0f) }
    val latestOnPrevious by rememberUpdatedState(onPrevious)
    val latestOnNext by rememberUpdatedState(onNext)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { direction = -1; onPrevious() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier.pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { dragAmount = 0f },
                    onHorizontalDrag = { _, drag -> dragAmount += drag },
                    onDragEnd = {
                        val threshold = 50f
                        if (dragAmount > threshold) {
                            direction = -1
                            latestOnPrevious()
                        } else if (dragAmount < -threshold) {
                            direction = 1
                            latestOnNext()
                        }
                    }
                )
            }
        ) {
            AnimatedContent(
                targetState = property,
                transitionSpec = {
                    slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it * direction }) + fadeIn() with
                            slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it * direction }) + fadeOut()
                }
            ) { targetProperty ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.alpha(if (isLocked) 0.5f else 1f).padding(vertical = 8.dp)
                ) {
                    if (isLocked) {
                        Icon(Icons.Default.Lock, contentDescription = "Locked", modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = targetProperty.name.replace("_", " "),
                        fontSize = 24.sp,
                        modifier = Modifier.widthIn(min = 200.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
        IconButton(onClick = { direction = 1; onNext() }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}
