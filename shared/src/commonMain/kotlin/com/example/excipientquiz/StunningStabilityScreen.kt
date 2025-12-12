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

private val stabilityAnswers = listOf(
    "Water soluble (hydrophilic) preservative",
    "Water soluble (hydrophylic) antioxidant",
    "Water- and fat soluble (hydrophilic and lipophilic) preservative",
    "Fat soluble (lipophilic) antioxidant"
)

private fun getStabilityClass(excipient: Excipient): String? {
    val function = excipient.function
    val aqsol = excipient.aqsol

    val isPreservative = function.contains("Preservative", ignoreCase = true)
    val isAntioxidant = function.contains("Antioxidant", ignoreCase = true)
    val isWaterSoluble = aqsol.contains("Water soluble", ignoreCase = true)
    val isLipophilic = aqsol.contains("Lipophilic", ignoreCase = true) || aqsol.contains("oils", ignoreCase = true)

    return when {
        isPreservative && isWaterSoluble && isLipophilic -> stabilityAnswers[2]
        isPreservative && isWaterSoluble -> stabilityAnswers[0]
        isAntioxidant && isWaterSoluble -> stabilityAnswers[1]
        isAntioxidant && isLipophilic && !isWaterSoluble -> stabilityAnswers[3]
        else -> null
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StunningStabilityScreen(onGameOver: (score: Int) -> Unit) {
    val questions = remember {
        excipients.mapNotNull { excipient ->
            getStabilityClass(excipient)?.let { answer -> excipient to answer }
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

    if (!isAnswered) {
        LaunchedEffect(currentIndex) {
            survivalTimer = 15
            while (survivalTimer > 0) {
                delay(1000)
                if (!isAnswered) survivalTimer--
            }
            if (!isAnswered) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onGameOver(score) }) { Text("Back") }
            Text("Score: $score", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    text = "How would you classify ${questionExcipient.name}?",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                stabilityAnswers.forEach { answer ->
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
