package com.example.excipientquiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import excipientquiz.shared.generated.resources.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class, ExperimentalResourceApi::class)
@Composable
fun ExcipientGameScreen(
    modifier: Modifier = Modifier,
    gameMode: GameMode,
    questionType: PropertyType,
    answerType: PropertyType,
    quizModes: Set<String>,
    onGameOver: () -> Unit,
    onNewAchievements: (List<Achievement>) -> Unit,
    onTierUnlocked: (ProgressionTier, String) -> Unit
) {
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
    var survivalTimer by remember { mutableStateOf(15) }

    if (gameMode == GameMode.EXCIPIENT_SPEEDRUN && !showResult) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                elapsedTime++
            }
        }
    }

    if (gameMode == GameMode.SURVIVAL && !showResult && !isQuestionAnswered) {
        LaunchedEffect(currentIndex) {
            survivalTimer = 15
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
            }
        }
    }

    if (questions.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(Res.string.no_questions), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onGameOver) {
                    Text(stringResource(Res.string.back))
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
            survivalTimer = 15
        } else {
            showResult = true
        }
    }

    if (isQuestionAnswered) {
        LaunchedEffect(currentIndex, isQuestionAnswered) {
            val delayTime = if (isAnswerCorrect) 500L else 3000L
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
                        Text(stringResource(Res.string.back))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (gameMode == GameMode.SURVIVAL) {
                            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                                repeat(lives) { 
                                    Icon(Icons.Default.Favorite, contentDescription = "Life", tint = Color.Red, modifier = Modifier.size(36.dp).padding(horizontal = 2.dp))
                                }
                            }
                        } else {
                            Text("", modifier = Modifier.weight(1f)) // Spacer
                        }
                        Text(stringResource(Res.string.score, score), fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        if (gameMode == GameMode.SURVIVAL) {
                            Text(
                                text = stringResource(Res.string.timer, survivalTimer),
                                fontSize = 20.sp,
                                color = if (survivalTimer <= 3) Color.Red else Color.Unspecified
                            )
                        } else {
                            Text(formatTime(elapsedTime), fontSize = 20.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                AnimatedContent(
                    targetState = currentIndex,
                    transitionSpec = {
                        SoundManager.playSound(SoundEffect.WHOOSH)
                        val direction = if (targetState > initialState) 1 else -1
                        (slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it * direction }) + fadeIn()).togetherWith(slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it * direction }) + fadeOut()) using SizeTransform(clip = false)
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
                            Spacer(modifier = Modifier.height(32.dp))
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
                                            score++
                                            SoundManager.playSound(SoundEffect.SUCCESS)
                                        } else {
                                            isAnswerCorrect = false
                                            mistakes++
                                            if (gameMode == GameMode.SURVIVAL) lives--
                                            selectedAnswer = it
                                            SoundManager.playSound(SoundEffect.FAIL)
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
                SoundManager.playSound(SoundEffect.ACHIEVEMENT)
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

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ResultScreen(
    gameMode: GameMode, questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>,
    score: Int, questionCount: Int, elapsedTime: Long, lives: Int,
    onGameOver: () -> Unit, onNewAchievements: (List<Achievement>) -> Unit, onTierUnlocked: (ProgressionTier, String) -> Unit
) {
    var isNewHighScore by remember { mutableStateOf(false) }
    var previousHighScoreString by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val wasSuccessful = if (gameMode == GameMode.SURVIVAL) lives > 0 else (score.toFloat() / questionCount) >= 0.75f
        if (wasSuccessful) {
            SoundManager.playSound(SoundEffect.SUCCESS_END)
            if (gameMode == GameMode.SURVIVAL) {
                ProgressionManager.unlockTimeAttack(questionType, answerType)
            }
        } else {
            SoundManager.playSound(SoundEffect.GAME_OVER)
        }
        val quizModeKey = if (quizModes.size > 1) "all" else quizModes.first()

        val progression = ProgressionManager.getProgression(quizModeKey)
        val oldTier = progression.tier
        val newTier = ProgressionManager.updateProgression(quizModeKey, score, elapsedTime, wasSuccessful)
        if (newTier.ordinal > oldTier.ordinal) {
            onTierUnlocked(newTier, quizModeKey)
        }

        val unlocked = AchievementManager.recordCompletionAndCheckForNewAchievements(
            gameMode = gameMode,
            quizModes = quizModes,
            questionType = questionType,
            answerType = answerType,
            wasSuccessful = wasSuccessful,
            score = score,
            time = elapsedTime,
            questionCount = questionCount
        )
        if (unlocked.isNotEmpty()) {
            onNewAchievements(unlocked)
        }

        val highScoreKey = "highscore_${gameMode}_${quizModeKey}_${questionType}_$answerType"

        if (gameMode == GameMode.EXCIPIENT_SPEEDRUN) {
            val previousHighScoreStringValue = ProgressionManager.getHighScoreString(highScoreKey)
            var oldScore = 0
            var oldTime = 0L

            if (previousHighScoreStringValue.isNotBlank()) {
                val parts = previousHighScoreStringValue.split('/')
                oldScore = parts.getOrNull(0)?.toIntOrNull() ?: 0
                oldTime = parts.getOrNull(1)?.toLongOrNull() ?: 0L
            }

            isNewHighScore = if (previousHighScoreStringValue.isBlank()) {
                true
            } else {
                score > oldScore || (score == oldScore && elapsedTime < oldTime)
            }

            if (isNewHighScore) {
                ProgressionManager.setHighScoreString(highScoreKey, "$score/$elapsedTime")
            }
            
            previousHighScoreString = if (previousHighScoreStringValue.isNotBlank()) {
                "$oldScore in ${formatTime(oldTime)}"
            } else {
                "-"
            }

        } else { // GameMode.SURVIVAL
            val previousHighScore = ProgressionManager.getHighScore(highScoreKey)
            isNewHighScore = score > previousHighScore
            if (isNewHighScore) {
                ProgressionManager.setHighScore(highScoreKey, score.toLong())
            }
            previousHighScoreString = if (previousHighScore > 0) previousHighScore.toString() else "-"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isNewHighScore) {
            Text(stringResource(Res.string.new_high_score), fontSize = 36.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(if (gameMode == GameMode.EXCIPIENT_SPEEDRUN) stringResource(Res.string.result_score_time_attack, score, questionCount) else stringResource(Res.string.result_score_survival, score), fontSize = 30.sp)
        if (gameMode == GameMode.EXCIPIENT_SPEEDRUN) Text(stringResource(Res.string.time, elapsedTime), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Text(stringResource(Res.string.previous_high_score, previousHighScoreString), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = onGameOver) { Text(stringResource(Res.string.back)) }
    }
}


@Composable
fun AchievementUnlockedDialog(achievements: List<Achievement>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (achievements.size > 1) stringResource(Res.string.achievements_unlocked) else stringResource(Res.string.achievement_unlocked), fontWeight = FontWeight.Bold) },
        text = { 
            Column {
                achievements.forEach { achievement ->
                    Text(achievement.name, fontWeight = FontWeight.Bold)
                    Text(achievement.description)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },
        confirmButton = {
                Button(onClick = onDismiss) { Text(stringResource(Res.string.awesome)) }
        }
    )
}

@Composable
fun ProgressionUnlockedDialog(tier: ProgressionTier, quizMode: String, onDismiss: () -> Unit) {
    val unlockedText = tier.unlockedExcipients.joinToString(", ")

    AlertDialog(
        onDismissRequest = onDismiss,
            title = { Text(stringResource(Res.string.new_quizzes_unlocked), fontWeight = FontWeight.Bold) },
            text = { Text(stringResource(Res.string.unlocked_quizzes_for, unlockedText, quizMode), textAlign = TextAlign.Center) },
            confirmButton = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = onDismiss) { Text(stringResource(Res.string.great)) }
                }
            }
    )
}

fun getPropertyValue(excipient: Excipient, propertyType: PropertyType): String {
    return when (propertyType) {
        PropertyType.FUNCTION -> excipient.function
        PropertyType.NAME -> excipient.name
        PropertyType.STRUCTURE -> excipient.imageRes
        PropertyType.ALTERNATIVE_NAME -> excipient.alternativename
        PropertyType.MOLECULE_TYPE -> excipient.moleculetype
    }
}

@OptIn(ExperimentalResourceApi::class)
private fun getDrawableResourceByName(name: String): DrawableResource? {
    return when (name) {
        "adepssolidus" -> Res.drawable.adepssolidus
        "agaragar" -> Res.drawable.agaragar
        "aluminiumchloride" -> Res.drawable.aluminiumchloride
        "arabicgum" -> Res.drawable.arabicgum
        "aspartame" -> Res.drawable.aspartame
        "bentonite" -> Res.drawable.bentonite
        "benzylalcohol" -> Res.drawable.benzylalcohol
        "benzalkoniumchloride" -> Res.drawable.benzalkoniumchloride
        "benzoicacid" -> Res.drawable.benzoicacid
        "betaines" -> Res.drawable.betaines
        "boricacid" -> Res.drawable.boricacid
        "brij" -> Res.drawable.brij
        "butylhydroxyanisol" -> Res.drawable.butylhydroxyanisol
        "butylhydroxytoluene" -> Res.drawable.butylhydroxytoluene
        "castearate" -> Res.drawable.castearate
        "cap" -> Res.drawable.cap
        "cetomacrogolemulsifyingwax" -> Res.drawable.cetomacrogolemulsifyingwax
        "cetostearylalcohol" -> Res.drawable.cetostearylalcohol
        "cetrimide" -> Res.drawable.cetrimide
        "cetrimideemulsifyingwax" -> Res.drawable.cetrimideemulsifyingwax
        "cetylalcohol" -> Res.drawable.cetylalcohol
        "chlorhexidine" -> Res.drawable.chlorhexidine
        "chlorobutanol" -> Res.drawable.chlorobutanol
        "chlorocresol" -> Res.drawable.chlorocresol
        "comperlan" -> Res.drawable.comperlan
        "cresol" -> Res.drawable.cresol
        "sio2" -> Res.drawable.sio2
        "cremophor" -> Res.drawable.cremophor
        "dbs" -> Res.drawable.dbs
        "disodiumedta" -> Res.drawable.disodiumedta
        "disodiumhydrogenphosphate" -> Res.drawable.disodiumhydrogenphosphate
        "ethanol" -> Res.drawable.ethanol
        "ethylcellulose" -> Res.drawable.ethylcellulose
        "eudragitl100" -> Res.drawable.eudragitl100
        "eudragitrlrs" -> Res.drawable.eudragitrlrs
        "gelatin" -> Res.drawable.gelatin
        "glucose" -> Res.drawable.glucose
        "glycerol" -> Res.drawable.glycerol
        "glycerolmonostearate" -> Res.drawable.glycerolmonostearate
        "hydroxyethylcellulose" -> Res.drawable.hydroxyethylcellulose
        "hydroxypropylcellulose" -> Res.drawable.hydroxypropylcellulose
        "hydroxypropylmethylcellulose" -> Res.drawable.hydroxypropylmethylcellulose
        "hpmcas" -> Res.drawable.hpmcas
        "hpmcp" -> Res.drawable.hpmcp
        "lactose" -> Res.drawable.lactose
        "lanette_n" -> Res.drawable.lanette_n
        "lanette_sx" -> Res.drawable.lanette_sx
        "lanolin" -> Res.drawable.lanolin
        "lecithin" -> Res.drawable.lecithin
        "magnesiumstearate" -> Res.drawable.magnesiumstearate
        "mannitol" -> Res.drawable.mannitol
        "menthol" -> Res.drawable.menthol
        "methylcellulose" -> Res.drawable.methylcellulose
        "methylparaben" -> Res.drawable.methylparaben
        "methyleneblue" -> Res.drawable.methyleneblue
        "cellulose" -> Res.drawable.cellulose
        "paraffin" -> Res.drawable.paraffin
        "polyacrylicacid" -> Res.drawable.polyacrylicacid
        "peanutoil" -> Res.drawable.peanutoil
        "pectin" -> Res.drawable.pectin
        "phenol" -> Res.drawable.phenol
        "phenylmercuricborate" -> Res.drawable.phenylmercuricborate
        "phenylmercuricnitrate" -> Res.drawable.phenylmercuricnitrate
        "propyleneglycol" -> Res.drawable.propyleneglycol
        "peg" -> Res.drawable.peg
        "pegcetylether" -> Res.drawable.pegcetylether
        "propylgallate" -> Res.drawable.propylgallate
        "polysorbate80" -> Res.drawable.polysorbate80
        "polyvinylalcohol" -> Res.drawable.polyvinylalcohol
        "pvp" -> Res.drawable.pvp
        "pvpva" -> Res.drawable.pvpva
        "potassiumcitrate" -> Res.drawable.potassiumcitrate
        "potassiumnitrate" -> Res.drawable.potassiumnitrate
        "potassiumtartrate" -> Res.drawable.potassiumtartrate
        "propylparaben" -> Res.drawable.propylparaben
        "sodiumalginate" -> Res.drawable.sodiumalginate
        "sodiumalkylsulfate" -> Res.drawable.sodiumalkylsulfate
        "sodiumbenzoate" -> Res.drawable.sodiumbenzoate
        "sodiumcetostearylsulfate" -> Res.drawable.sodiumcetostearylsulfate
        "nacl" -> Res.drawable.nacl
        "sodiumdodecylsulfate" -> Res.drawable.sodiumdodecylsulfate
        "sodiumbisulfite" -> Res.drawable.sodiumbisulfite
        "sodiumcarboxymethylcellulose" -> Res.drawable.sodiumcarboxymethylcellulose
        "sodiumcitrate" -> Res.drawable.sodiumcitrate
        "sodiumlaurylethersulfate" -> Res.drawable.sodiumlaurylethersulfate
        "sodiumpyrophosphate" -> Res.drawable.sodiumpyrophosphate
        "sodiummetabisulfite" -> Res.drawable.sodiummetabisulfite
        "sodiumsulfite" -> Res.drawable.sodiumsulfite
        "sodiumthiosulfite" -> Res.drawable.sodiumthiosulfite
        "sorbicacid" -> Res.drawable.sorbicacid
        "span80" -> Res.drawable.span80
        "starch" -> Res.drawable.starch
        "stearicacid" -> Res.drawable.stearicacid
        "sudaniii" -> Res.drawable.sudaniii
        "talc" -> Res.drawable.talc
        "thiomersal" -> Res.drawable.thiomersal
        "tragacanth" -> Res.drawable.tragacanth
        "triethanolaminestearate" -> Res.drawable.triethanolaminestearate
        "tec" -> Res.drawable.tec
        "vitamine" -> Res.drawable.vitamine
        "vitaminc" -> Res.drawable.vitaminc
        "wax" -> Res.drawable.wax
        "xanthangum" -> Res.drawable.xanthangum
        else -> null
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun QuestionContent(questionType: PropertyType, answerType: PropertyType, excipient: Excipient) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.question_prompt, answerType.name.lowercase().replace("_", " "), questionType.name.lowercase().replace("_", " ")),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (questionType) {
            PropertyType.NAME -> Text(excipient.name, fontSize = 24.sp, textAlign = TextAlign.Center)
            PropertyType.FUNCTION -> Text(excipient.function, fontSize = 24.sp, textAlign = TextAlign.Center)
            PropertyType.STRUCTURE -> getDrawableResourceByName(excipient.imageRes)?.let {
                SharedImage(image = it, modifier = Modifier.size(200.dp).fillMaxWidth(), contentScale = ContentScale.Fit)
            }
            PropertyType.ALTERNATIVE_NAME -> Text(excipient.alternativename, fontSize = 24.sp, textAlign = TextAlign.Center)
            PropertyType.MOLECULE_TYPE -> Text(excipient.moleculetype, fontSize = 24.sp, textAlign = TextAlign.Center)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AnswerContent(
    answerType: PropertyType,
    options: List<String>,
    correctAnswer: String,
    isAnswered: Boolean,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit
) {
    if (answerType == PropertyType.STRUCTURE) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val horizontalPadding = 16.dp
            val availableWidth = maxWidth - (horizontalPadding * 2)
            val availableHeight = maxHeight
            val tileSize = min(availableWidth / 2, availableHeight / 2)

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (i in 0..1) {
                        val option = options[i]
                        val isSelected = selectedAnswer != null && option.equals(selectedAnswer, ignoreCase = true)
                        val isCorrect = option.equals(correctAnswer, ignoreCase = true)
                        ImageTile(
                            modifier = Modifier.size(tileSize),
                            imageName = option,
                            isAnswered = isAnswered,
                            isSelected = isSelected,
                            isCorrect = isCorrect,
                            onClick = { onAnswerSelected(option) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    for (i in 2..3) {
                        val option = options[i]
                        val isSelected = selectedAnswer != null && option.equals(selectedAnswer, ignoreCase = true)
                        val isCorrect = option.equals(correctAnswer, ignoreCase = true)
                        ImageTile(
                            modifier = Modifier.size(tileSize),
                            imageName = option,
                            isAnswered = isAnswered,
                            isSelected = isSelected,
                            isCorrect = isCorrect,
                            onClick = { onAnswerSelected(option) }
                        )
                    }
                }
            }
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(options) { option ->
                val isSelected = selectedAnswer != null && option.equals(selectedAnswer, ignoreCase = true)
                val isCorrect = option.equals(correctAnswer, ignoreCase = true)
                Button(
                    onClick = { onAnswerSelected(option) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            isAnswered && isCorrect -> Color.Green.copy(alpha = 0.5f)
                            isAnswered && isSelected && !isCorrect -> Color.Red.copy(alpha = 0.5f)
                            else -> MaterialTheme.colorScheme.primary
                        }
                    ),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(option, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageTile(
    modifier: Modifier = Modifier,
    imageName: String,
    isAnswered: Boolean,
    isSelected: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isAnswered && isCorrect -> Color.Green.copy(alpha = 0.5f)
                isAnswered && isSelected && !isCorrect -> Color.Red.copy(alpha = 0.5f)
                else -> MaterialTheme.colorScheme.surface
            }
        )
    ) {
        getDrawableResourceByName(imageName)?.let {
            SharedImage(image = it, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Fit)
        }
    }
}

fun formatTime(time: Long): String {
    val seconds = time % 60
    val minutes = (time / 60) % 60
    return "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
}
