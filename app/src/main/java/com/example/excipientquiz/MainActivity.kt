package com.example.excipientquiz

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excipientquiz.ui.theme.ExcipientQuizTheme
import com.example.excipientquiz.CreditsScreen
import com.example.excipientquiz.SpecialGameModesScreen
import com.example.excipientquiz.EmulsionTypesScreen
import com.example.excipientquiz.CelluloseConnoisseurScreen
import com.example.excipientquiz.LanetteLingeringScreen
import com.example.excipientquiz.SpecialModeResultScreen
import com.example.excipientquiz.EncyclopediaScreen
import com.example.excipientquiz.StunningStabilityScreen
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLocale(this)
        setContent {
            ExcipientQuizTheme {
                AppContent()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        SoundManager.resumeMusic(this)
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pauseBackgroundMusic()
    }

    override fun onStop() {
        super.onStop()
        SoundManager.abandonAudioFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        SoundManager.stopBackgroundMusic()
    }

    private fun updateLocale(context: Context) {
        val language = SettingsManager.getLanguage(context)
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}

@Composable
fun AppContent() {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE) }
    val hasSeenTutorial = remember { mutableStateOf(prefs.getBoolean("hasSeenTutorial", false)) }

    var currentScreen by remember { mutableStateOf("start") }

    val savedModes = remember { prefs.getStringSet("lastSelectedQuizModes", null) }
    var selectedQuizModes by remember {
        mutableStateOf(savedModes ?: setOf("Creams & Emulsions"))
    }

    var selectedGameMode by remember { mutableStateOf(GameMode.EXCIPIENT_SPEEDRUN) }
    var selectedQuestionType by remember { mutableStateOf(PropertyType.NAME) }
    var selectedAnswerType by remember { mutableStateOf(PropertyType.STRUCTURE) }
    var selectedExcipient by remember { mutableStateOf<Excipient?>(null) }

    val encyclopediaListState = rememberLazyListState()
    var encyclopediaSearchText by remember { mutableStateOf("") }
    var encyclopediaSelectedFunction by remember { mutableStateOf("All Functions") }

    var specialModeId by remember { mutableStateOf<String?>(null) }
    var specialModeScore by remember { mutableStateOf(0) }
    
    LaunchedEffect(currentScreen, selectedGameMode) {
        when (currentScreen) {
            "game" -> {
                val musicType = if (selectedGameMode == GameMode.EXCIPIENT_SPEEDRUN) {
                    SoundManager.MusicType.EXCIPIENT_SPEEDRUN
                } else {
                    SoundManager.MusicType.SURVIVAL
                }
                SoundManager.playMusic(context, musicType)
            }
            "emulsion_types", "cellulose_connoisseur", "lanette_lingering", "stunning_stability" -> {
                SoundManager.playMusic(context, SoundManager.MusicType.SURVIVAL)
            }
            else -> SoundManager.playMusic(context, SoundManager.MusicType.MENU)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White,
                            colorResource(id = R.color.light_blue_bg)
                        )
                    )
                )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val dotColor = Color(0xFFD0D0D0)
                val dotRadius = 1.dp.toPx()
                val spacing = 12.dp.toPx()
                var x = 0f
                while (x < size.width) {
                    var y = 0f
                    while (y < size.height) {
                        drawCircle(color = dotColor, radius = dotRadius, center = Offset(x, y))
                        y += spacing
                    }
                    x += spacing
                }
            }

            if (!hasSeenTutorial.value) {
                TutorialScreen(onComplete = {
                    prefs.edit().putBoolean("hasSeenTutorial", true).apply()
                    hasSeenTutorial.value = true
                    currentScreen = "start"
                })
            } else {
                when (currentScreen) {
                    "start" -> ExcipientGameStartScreen(
                        selectedQuizModes = selectedQuizModes,
                        questionType = selectedQuestionType,
                        answerType = selectedAnswerType,
                        onQuestionTypeChange = { selectedQuestionType = it },
                        onAnswerTypeChange = { selectedAnswerType = it },
                        onStartChallenge = { currentScreen = "mode_selection" },
                        onShowOptions = { currentScreen = "options" },
                        onShowAchievements = { currentScreen = "achievements" },
                        onShowEncyclopedia = { currentScreen = "encyclopedia" },
                        onShowProgression = { currentScreen = "progression" }
                    )
                    "settings" -> SettingsScreen(
                        onBack = { currentScreen = "start" },
                        onShowTutorial = { hasSeenTutorial.value = false },
                        onShowCredits = { currentScreen = "credits" }
                    )
                    "credits" -> CreditsScreen(onBack = { currentScreen = "start" })
                    "progression" -> ProgressionScreen(onBack = { currentScreen = "start" })
                    "mode_selection" -> GameModeSelectionScreen(
                        questionType = selectedQuestionType,
                        answerType = selectedAnswerType,
                        quizModes = selectedQuizModes,
                        onModeSelected = { 
                            selectedGameMode = it
                            currentScreen = "game"
                        },
                        onBack = { currentScreen = "start" }
                    )
                    "options" -> OptionsScreen(availableModes = quizModes, initialSelection = selectedQuizModes, onSave = { newModes ->
                        selectedQuizModes = newModes
                        prefs.edit().putStringSet("lastSelectedQuizModes", newModes).apply()
                        currentScreen = "start" }, onBack = { currentScreen = "start" }, onShowSpecialModes = { currentScreen = "special_modes" }
                    )
                    "achievements" -> AchievementsScreen(onBack = { currentScreen = "start" })
                    "encyclopedia" -> EncyclopediaScreen(
                        listState = encyclopediaListState,
                        selectedFunction = encyclopediaSelectedFunction,
                        onSelectedFunctionChange = { encyclopediaSelectedFunction = it },
                        searchText = encyclopediaSearchText,
                        onSearchTextChange = { encyclopediaSearchText = it },
                        onExcipientSelected = { selectedExcipient = it; currentScreen = "excipient_detail" },
                        onBack = { currentScreen = "start" }
                    )
                    "special_modes" -> SpecialGameModesScreen(
                        onBack = { currentScreen = "options" },
                        onModeSelected = { modeId ->
                            specialModeId = modeId
                            currentScreen = modeId
                        }
                    )
                    "lanette_lingering" -> LanetteLingeringScreen(onGameOver = { score ->
                        specialModeScore = score
                        currentScreen = "special_mode_result"
                    })
                    "cellulose_connoisseur" -> CelluloseConnoisseurScreen(onGameOver = { score ->
                        specialModeScore = score
                        currentScreen = "special_mode_result"
                    })
                    "emulsion_types" -> EmulsionTypesScreen(onGameOver = { score ->
                        specialModeScore = score
                        currentScreen = "special_mode_result"
                    })
                    "stunning_stability" -> StunningStabilityScreen(onGameOver = { score ->
                        specialModeScore = score
                        currentScreen = "special_mode_result"
                    })
                    "special_mode_result" -> specialModeId?.let {
                        SpecialModeResultScreen(
                            modeId = it,
                            score = specialModeScore,
                            onBack = { currentScreen = "special_modes" }
                        )
                    }

                    "excipient_detail" -> selectedExcipient?.let { ExcipientDetailScreen(excipient = it, onBack = { currentScreen = "encyclopedia" }) }
                    "game" -> ExcipientGameScreen(gameMode = selectedGameMode, questionType = selectedQuestionType, answerType = selectedAnswerType, quizModes = selectedQuizModes, onGameOver = { currentScreen = "start" })
                }

                if (currentScreen == "start") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        DynamicHighScoreDisplay(
                            questionType = selectedQuestionType, 
                            answerType = selectedAnswerType, 
                            quizModes = selectedQuizModes
                        )
                        IconButton(onClick = { currentScreen = "settings" }) {
                            Icon(painter = painterResource(id = R.drawable.ic_settings), contentDescription = "Settings")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DynamicHighScoreDisplay(questionType: PropertyType, answerType: PropertyType, quizModes: Set<String>) {
    val context = LocalContext.current
    val excipientSpeedrunHighScore = ScoreManager.getTimeAttackHighScore(context, questionType, answerType, quizModes)
    val survivalHighScore = ScoreManager.getSurvivalHighScore(context, questionType, answerType, quizModes)

    Column(horizontalAlignment = Alignment.Start) {
        Text("High Scores", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        if (excipientSpeedrunHighScore.first > 0 || survivalHighScore > 0) {
            if (excipientSpeedrunHighScore.first > 0) {
                Text("Excipient Speedrun: ${excipientSpeedrunHighScore.first} (${excipientSpeedrunHighScore.second}s)", fontSize = 14.sp)
            }
            if (survivalHighScore > 0) {
                Text("Survival: $survivalHighScore", fontSize = 14.sp)
            }
        } else {
            Text("No high scores yet!", fontSize = 14.sp)
        }
    }
}
