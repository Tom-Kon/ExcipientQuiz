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
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ExcipientGameScreen(
    modifier: Modifier = Modifier,
    gameMode: GameMode,
    questionType: PropertyType,
    answerType: PropertyType,
    quizModes: Set<String>,
    onGameOver: () -> Unit
) {
    val context = LocalContext.current
    val questions = remember(quizModes, questionType, answerType) {
        quizModes.flatMap { mode -> com.example.excipientquiz.quizModes[mode] ?: emptyList() }
            .filter {
                val qVal = getPropertyValue(it, questionType)
                val aVal = getPropertyValue(it, answerType)
                (qVal.isNotBlank() && qVal != "none") && (aVal.isNotBlank() && aVal != "none")
            }
            .distinct().shuffled()
    }

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var lives by remember { mutableStateOf(3) }
    var elapsedTime by remember { mutableStateOf(0L) }
    var showResult by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isQuestionAnswered by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var mistakes by remember { mutableStateOf(0) }
    var newlyUnlockedAchievements by remember { mutableStateOf<List<Achievement>>(emptyList()) }
    var newlyUnlockedTierInfo by remember { mutableStateOf<Pair<ProgressionTier, String>?>(null) }
    var survivalTimer by remember { mutableStateOf(10) }

    if (gameMode == GameMode.TIME_ATTACK && !showResult) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                elapsedTime++
            }
        }
    }

    if (gameMode == GameMode.SURVIVAL && !showResult && !isQuestionAnswered) {
        LaunchedEffect(currentIndex) {
            survivalTimer = 10
            while (survivalTimer > 0) {
                delay(1000)
                if (!isQuestionAnswered) {
                    survivalTimer--
                }
            }
            if (!isQuestionAnswered) {
                mistakes++
                lives--
                isQuestionAnswered = true
                isAnswerCorrect = false
                SoundManager.playSound(context, R.raw.fail)
            }
        }
    }

    if (questions.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(id = R.string.gamescreen_no_questions), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onGameOver) {
                    Text(stringResource(id = R.string.gamemode_button_back))
                }
            }
        }
        return
    }

    fun nextQuestion() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            selectedAnswer = null
            isQuestionAnswered = false
            isAnswerCorrect = false
            survivalTimer = 10
        } else {
            showResult = true
        }
    }

    if (isQuestionAnswered) {
        LaunchedEffect(currentIndex, isQuestionAnswered) {
            val delayTime = if (isAnswerCorrect) 500L else 3000L
            if(!isAnswerCorrect) SoundManager.playSound(context, R.raw.fail)
            delay(delayTime)
            if (gameMode == GameMode.SURVIVAL && lives <= 0) {
                showResult = true
            } else {
                nextQuestion()
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (showResult) {
            ResultScreen(
                gameMode = gameMode,
                questionType = questionType,
                answerType = answerType,
                quizModes = quizModes,
                score = score,
                questionCount = questions.size,
                elapsedTime = elapsedTime,
                lives = lives,
                onGameOver = onGameOver,
                onNewAchievements = { newlyUnlockedAchievements = it },
                onTierUnlocked = { tier, mode -> newlyUnlockedTierInfo = tier to mode }
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = onGameOver) {
                        Text(stringResource(id = R.string.common_back))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(stringResource(id = R.string.gamescreen_label_score, score), fontSize = 20.sp)
                        if (gameMode == GameMode.SURVIVAL) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(id = R.string.gamescreen_label_timer, survivalTimer),
                                fontSize = 20.sp,
                                color = if (survivalTimer <= 3) Color.Red else Color.Unspecified
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (gameMode == GameMode.SURVIVAL) {
                     Row(horizontalArrangement = Arrangement.Center) {
                        repeat(lives) { 
                            Icon(Icons.Default.Favorite, contentDescription = "Life", tint = Color.Red, modifier = Modifier.size(36.dp).padding(horizontal = 2.dp))
                        }
                    }
                } else { 
                    Text("Time: ${elapsedTime}s", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(84.dp))
                
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
                    val question = questions.getOrNull(targetIndex)
                    if (question != null) {
                        val correctAnswer = getPropertyValue(question, answerType)
                        val options = remember(targetIndex) {
                            val answerPool = questions.map { getPropertyValue(it, answerType) }.distinct()
                            val wrongAnswers = answerPool.filter { it != correctAnswer }.shuffled().take(3)
                            val finalOptions = (wrongAnswers + correctAnswer).shuffled()
                            if (finalOptions.size < 4) {
                                val paddingNeeded = 4 - finalOptions.size
                                val paddingAnswers = excipients.map { getPropertyValue(it, answerType) }
                                    .filter { it.isNotBlank() && it != "none" && it !in finalOptions }
                                    .distinct()
                                    .shuffled()
                                    .take(paddingNeeded)
                                (finalOptions + paddingAnswers).shuffled()
                            } else {
                                finalOptions
                            }
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp)) {
                            QuestionContent(questionType, answerType, question)
                            Spacer(modifier = Modifier.weight(0.001f))
                            AnswerContent(
                                answerType = answerType,
                                options = options,
                                correctAnswer = correctAnswer,
                                isAnswered = isQuestionAnswered,
                                selectedAnswer = selectedAnswer,
                                onAnswerSelected = {
                                    if (!isQuestionAnswered) {
                                        isQuestionAnswered = true
                                        if (it == correctAnswer) {
                                            isAnswerCorrect = true
                                            SoundManager.playSound(context, R.raw.success)
                                            score++
                                        } else {
                                            isAnswerCorrect = false
                                            mistakes++
                                            if (gameMode == GameMode.SURVIVAL) lives--
                                            selectedAnswer = it
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (newlyUnlockedAchievements.isNotEmpty()) {
            SideEffect {
                SoundManager.playSound(context, R.raw.achievement)
            }
            AchievementUnlockedDialog(achievements = newlyUnlockedAchievements, onDismiss = { newlyUnlockedAchievements = emptyList() })
        }

        if (newlyUnlockedTierInfo != null) {
            ProgressionUnlockedDialog(
                tier = newlyUnlockedTierInfo!!.first,
                quizMode = newlyUnlockedTierInfo!!.second,
                onDismiss = { newlyUnlockedTierInfo = null }
            )
        }
    }
}

@Composable
private fun ResultScreen(
    gameMode: GameMode, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>,
    score: Int, questionCount: Int, elapsedTime: Long, lives: Int,
    onGameOver: () -> Unit, onNewAchievements: (List<Achievement>) -> Unit, onTierUnlocked: (ProgressionTier, String) -> Unit
) {
    val context = LocalContext.current
    var isNewHighScore by remember { mutableStateOf(false) }
    var previousHighScoreString by remember { mutableStateOf("none") }

    LaunchedEffect(Unit) {
        val wasSuccessful = if(gameMode == GameMode.SURVIVAL) lives > 0 else (score.toFloat() / questionCount) >= 0.5f

        if (gameMode == GameMode.TIME_ATTACK) {
            val oldHighScore = ScoreManager.getTimeAttackHighScore(context, questionType, answerType, quizModes)
            if (oldHighScore.first > 0) previousHighScoreString = "${oldHighScore.first} (${oldHighScore.second}s)"
            if (score > oldHighScore.first || (score == oldHighScore.first && elapsedTime < oldHighScore.second)) {
                isNewHighScore = true
                ScoreManager.saveTimeAttackHighScore(context, questionType, answerType, quizModes, score, elapsedTime)
            }
        } else { // SURVIVAL
            val oldHighScore = ScoreManager.getSurvivalHighScore(context, questionType, answerType, quizModes)
            if (oldHighScore > 0) previousHighScoreString = "$oldHighScore"
            if (score > oldHighScore) {
                isNewHighScore = true
                ScoreManager.saveSurvivalHighScore(context, questionType, answerType, quizModes, score)
            }
        }
        
        if (wasSuccessful) SoundManager.playSound(context, R.raw.succesend) else SoundManager.playSound(context, R.raw.gameover)

        val isProgressionMode = gameMode == GameMode.SURVIVAL && lives > 0 && quizModes.size == 1 && quizModes.first() !in setOf("All Excipients", "Other")
        if (isProgressionMode) {
            val currentQuizMode = quizModes.first()
            val currentTier = ProgressionManager.getProgressionTier(context, currentQuizMode)
            if (currentTier == ProgressionTier.LOCKED) {
                val newTier = ProgressionTier.ALTERNATIVE_NAMES
                ProgressionManager.setProgressionTier(context, currentQuizMode, newTier)
                onTierUnlocked(newTier, currentQuizMode)
            } else if (currentTier == ProgressionTier.ALTERNATIVE_NAMES) {
                val newTier = ProgressionTier.FULLY_UNLOCKED
                ProgressionManager.setProgressionTier(context, currentQuizMode, newTier)
                onTierUnlocked(newTier, currentQuizMode)
            }
        }

        val newAchievements = AchievementManager.recordCompletionAndCheckForNewAchievements(context, gameMode, quizModes, questionType, answerType, wasSuccessful)
        if(newAchievements.isNotEmpty()) {
            onNewAchievements(newAchievements)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isNewHighScore) {
            Text(stringResource(id = R.string.resultscreen_new_highscore), fontSize = 36.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(if (gameMode == GameMode.TIME_ATTACK) stringResource(id = R.string.resultscreen_score_time_attack, score, questionCount) else stringResource(id = R.string.resultscreen_score_survival, score), fontSize = 30.sp)
        if (gameMode == GameMode.TIME_ATTACK) Text("Time: ${elapsedTime}s", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(stringResource(id = R.string.resultscreen_previous_highscore, previousHighScoreString), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onGameOver) { Text(stringResource(id = R.string.resultscreen_button_back)) }
    }
}

@Composable
fun AchievementUnlockedDialog(achievements: List<Achievement>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (achievements.size > 1) stringResource(id = R.string.dialog_achievement_title_multiple) else stringResource(id = R.string.dialog_achievement_title_single), fontWeight = FontWeight.Bold) },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                achievements.forEachIndexed { index, achievement ->
                    if(index > 0) Spacer(modifier = Modifier.height(16.dp))
                    Image(painter = painterResource(id = achievement.imageRes), contentDescription = "Achievement Badge", modifier = Modifier.size(96.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(achievement.name, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = achievement.descriptionResId, *achievement.descriptionFormatArgs.toTypedArray()),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        confirmButton = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = onDismiss) { Text(stringResource(id = R.string.dialog_achievement_button)) }
            }
        }
    )
}

@Composable
fun ProgressionUnlockedDialog(
    tier: ProgressionTier,
    quizMode: String,
    onDismiss: () -> Unit
) {
    val unlockedText = when (tier) {
        ProgressionTier.ALTERNATIVE_NAMES -> stringResource(id = R.string.dialog_progression_tier2)
        ProgressionTier.FULLY_UNLOCKED -> stringResource(id = R.string.dialog_progression_tier3)
        else -> ""
    }

    if (unlockedText.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(stringResource(id = R.string.dialog_progression_title), fontWeight = FontWeight.Bold) },
            text = { Text(stringResource(id = R.string.dialog_progression_body_time_attack, unlockedText, quizMode), textAlign = TextAlign.Center) },
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = onDismiss) { Text(stringResource(id = R.string.dialog_progression_button)) }
                }
            }
        )
    }
}

private fun getPropertyValue(excipient: Excipient, propertyType: PropertyType): String {
    return when (propertyType) {
        PropertyType.NAME -> excipient.name
        PropertyType.STRUCTURE -> excipient.imageRes.toString()
        PropertyType.FUNCTION -> excipient.function
        PropertyType.ALTERNATIVE_NAME -> excipient.alternativename
        PropertyType.MOLECULE_TYPE -> excipient.moleculetype
    }.trim()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageTile(excipient: Excipient, isAnswered: Boolean, isSelected: Boolean, isCorrect: Boolean, onClick: () -> Unit) {
    val tileColor = if (isAnswered) if (isCorrect) Color.Green else if (isSelected) Color.Red else Color.White else Color.White
    Card(
        onClick = onClick,
        modifier = Modifier.size(150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = tileColor)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = excipient.imageRes),
                contentDescription = excipient.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
private fun QuestionContent(questionType: PropertyType, answerType: PropertyType, excipient: Excipient) {
    Text(
        text = stringResource(id = R.string.gamescreen_question_prompt, answerType.name.lowercase().replace("_", " "), questionType.name.lowercase().replace("_", " ")),
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    when (questionType) {
        PropertyType.STRUCTURE -> Image(
            painter = painterResource(id = excipient.imageRes),
            contentDescription = "Question Image",
            modifier = Modifier.size(200.dp).fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        else -> Text(getPropertyValue(excipient, questionType), fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnswerContent(
    answerType: PropertyType, options: List<String>, correctAnswer: String, isAnswered: Boolean, selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit
) {
    when (answerType) {
        PropertyType.STRUCTURE -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (i in 0..1) {
                        val optionValue = options.getOrNull(i) ?: continue
                        val excipientForImage = excipients.firstOrNull { getPropertyValue(it, answerType) == optionValue }
                        if (excipientForImage != null) {
                            val isCorrect = optionValue == correctAnswer
                            val isSelected = optionValue == selectedAnswer
                            ImageTile(excipientForImage, isAnswered, isSelected, isCorrect) { onAnswerSelected(optionValue) }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (i in 2..3) {
                        val optionValue = options.getOrNull(i) ?: continue
                        val excipientForImage = excipients.firstOrNull { getPropertyValue(it, answerType) == optionValue }
                        if (excipientForImage != null) {
                            val isCorrect = optionValue == correctAnswer
                            val isSelected = optionValue == selectedAnswer
                            ImageTile(excipientForImage, isAnswered, isSelected, isCorrect) { onAnswerSelected(optionValue) }
                        }
                    }
                }
            }
        }
        else -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                options.forEach { option ->
                    val isCorrect = option == correctAnswer
                    val isSelected = option == selectedAnswer
                    val buttonColor = if (isAnswered) if (isCorrect) Color.Green else if (isSelected) Color.Red else Color.White else Color.White
                    Button(
                        onClick = { onAnswerSelected(option) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        Text(option, color = Color.Black)
                    }
                }
            }
        }
    }
}

