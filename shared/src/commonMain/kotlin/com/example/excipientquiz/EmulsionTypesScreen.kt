package com.example.excipientquiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val emulsionAnswers = listOf("Simple O/W", "Simple W/O", "Complex O/W", "Complex W/O")

private fun getEmulsionType(excipient: Excipient): String? {
    val function = excipient.function
    return when {
        function.contains("(O/W)", ignoreCase = true) && !function.contains("Complex", ignoreCase = true) -> "Simple O/W"
        function.contains("(W/O)", ignoreCase = true) && !function.contains("Complex", ignoreCase = true) -> "Simple W/O"
        function.contains("(Complex O/W)", ignoreCase = true) -> "Complex O/W"
        function.contains("(Complex W/O)", ignoreCase = true) -> "Complex W/O"
        else -> null
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EmulsionTypesScreen(onGameOver: (score: Int) -> Unit) {
    val questions = remember {
        excipients.mapNotNull { excipient ->
            getEmulsionType(excipient)?.let { answer -> excipient to answer }
        }.shuffled()
    }

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var lives by remember { mutableStateOf(3) }
    var survivalTimer by remember { mutableStateOf(15) }
    var isAnswered by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    fun handleIncorrectAnswer() {
        lives--
        SoundManager.playSound(SoundEffect.FAIL)
    }

    // Timer logic
    if (!isAnswered) {
        LaunchedEffect(currentIndex) {
            survivalTimer = 15
            while (survivalTimer > 0) {
                delay(1000)
                if (!isAnswered) {
                    survivalTimer--
                }
            }
            if (!isAnswered) { // Time ran out
                isAnswered = true
                handleIncorrectAnswer()
            }
        }
    }

    fun nextQuestion() {
        if (lives > 0 && currentIndex < questions.size - 1) {
            currentIndex++
            isAnswered = false
            selectedAnswer = null
            survivalTimer = 15
        } else {
            onGameOver(score)
        }
    }

    if (isAnswered) {
        LaunchedEffect(currentIndex, isAnswered) {
            delay(2000)
            nextQuestion()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onGameOver(score) }) { Text("Back") }
            Text("Score: $score", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lives and Timer
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(lives) {
                    Icon(Icons.Default.Favorite, contentDescription = "Life", tint = Color.Red, modifier = Modifier.size(36.dp).padding(horizontal = 2.dp))
                }
            }
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "Timer: $survivalTimer",
                fontSize = 24.sp,
                color = if (survivalTimer <= 5) Color.Red else Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                val direction = if (targetState > initialState) 1 else -1
                slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it * direction }) + fadeIn() with
                        slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it * direction }) + fadeOut() using
                        SizeTransform(clip = false)
            },
            modifier = Modifier.weight(1f)
        ) { targetIndex ->
            val (questionExcipient, correctAnswer) = questions[targetIndex]

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(
                    text = "What type of emulsifier is ${questionExcipient.name}?",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                emulsionAnswers.forEach { answer ->
                    val isCorrect = answer == correctAnswer
                    val buttonColor = if (isAnswered) {
                        if (isCorrect) Color.Green else if (selectedAnswer == answer) Color.Red else Color.White
                    } else {
                        Color.White
                    }

                    Button(
                        onClick = {
                            if (!isAnswered) {
                                isAnswered = true
                                selectedAnswer = answer
                                if (isCorrect) {
                                    score++
                                    SoundManager.playSound(SoundEffect.SUCCESS)
                                } else {
                                    handleIncorrectAnswer()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Text(answer, color = Color.Black)
                    }
                }
            }
        }
    }
}
