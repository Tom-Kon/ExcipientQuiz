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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CelluloseConnoisseurScreen(onGameOver: (score: Int) -> Unit) {
    val context = LocalContext.current

    val gameData = remember {
        val questions = excipients.filter { 
            it.name.contains("Cellulose", ignoreCase = true) || 
            it.name.contains("methylcellulose", ignoreCase = true)
        }.shuffled()

        questions.map { questionExcipient ->
            val otherOptions = (questions - questionExcipient).shuffled().take(3)
            val options = (otherOptions + questionExcipient).shuffled()
            questionExcipient to options
        }
    }

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var lives by remember { mutableStateOf(3) }
    var survivalTimer by remember { mutableStateOf(15) }
    var isAnswered by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableStateOf<Excipient?>(null) }

    fun handleIncorrectAnswer() {
        lives--
        SoundManager.playSound(context, R.raw.fail)
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
        if (lives > 0 && currentIndex < gameData.size - 1) {
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
            Button(onClick = { onGameOver(score) }) { Text(stringResource(id = R.string.common_back)) }
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
                text = stringResource(id = R.string.gamescreen_label_timer, survivalTimer),
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
            val (questionExcipient, options) = gameData[targetIndex]

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(
                    text = questionExcipient.name,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Answer grid
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        for(i in 0..1) {
                            val option = options[i]
                            ImageTile(
                                excipient = option,
                                isAnswered = isAnswered,
                                isSelected = selectedAnswer == option,
                                isCorrect = questionExcipient == option,
                                onClick = {
                                    if (!isAnswered) {
                                        isAnswered = true
                                        selectedAnswer = option
                                        if (option == questionExcipient) {
                                            score++
                                            SoundManager.playSound(context, R.raw.success)
                                        } else {
                                            handleIncorrectAnswer()
                                        }
                                    }
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                         for(i in 2..3) {
                            val option = options[i]
                            ImageTile(
                                excipient = option,
                                isAnswered = isAnswered,
                                isSelected = selectedAnswer == option,
                                isCorrect = questionExcipient == option,
                                onClick = {
                                    if (!isAnswered) {
                                        isAnswered = true
                                        selectedAnswer = option
                                        if (option == questionExcipient) {
                                            score++
                                            SoundManager.playSound(context, R.raw.success)
                                        } else {
                                            handleIncorrectAnswer()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
