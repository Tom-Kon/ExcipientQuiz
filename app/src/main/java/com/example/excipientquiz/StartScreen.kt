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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
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
    onShowProgression: () -> Unit
) {
    val context = LocalContext.current
    val propertyTypes = PropertyType.values()

    val isPairValid = questionType != answerType
    val isPlayable = ProgressionManager.isPlayable(context, selectedQuizModes, questionType, answerType, GameMode.SURVIVAL) || 
                     ProgressionManager.isPlayable(context, selectedQuizModes, questionType, answerType, GameMode.EXCIPIENT_SPEEDRUN)


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
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            text = stringResource(id = R.string.start_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(stringResource(id = R.string.start_label_question), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        PropertySelector(property = questionType, onPrevious = { updateQuestionType(-1) }, onNext = { updateQuestionType(1) }, isLocked = !isPlayable)

        Spacer(modifier = Modifier.height(32.dp))

        Text(stringResource(id = R.string.start_label_answer), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        PropertySelector(property = answerType, onPrevious = { updateAnswerType(-1) }, onNext = { updateAnswerType(1) }, isLocked = !isPlayable)

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onStartChallenge,
            enabled = isPlayable && isPairValid
        ) {
            Text(stringResource(id = R.string.start_button_challenge), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(56.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = onShowOptions) {
                    Text(stringResource(id = R.string.start_button_options))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onShowProgression) {
                    Text(stringResource(id = R.string.start_button_progression))
                }
            }
            Text(
                text = stringResource(id = R.string.start_label_active_modes, selectedQuizModes.joinToString()),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 25.dp)) {
                Button(onClick = onShowAchievements) {
                    Text(stringResource(id = R.string.start_button_achievements))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onShowEncyclopedia) {
                    Text(stringResource(id = R.string.start_button_encyclopedia))
                }
            }
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
                        Icon(Icons.Default.Lock, contentDescription = stringResource(id = R.string.common_locked), modifier = Modifier.size(24.dp))
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
