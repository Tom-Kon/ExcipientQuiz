package com.example.excipientquiz

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.intl.Locale

@Composable
fun App() {
    // 1. State holding the current language code
    var currentLanguageCode by remember { mutableStateOf(SettingsManager.getLanguage()) }

    // 2. Version counter to force recomposition when language changes
    var localeVersion by remember { mutableStateOf(0) }

    CompositionLocalProvider(LocalLocale provides Locale(currentLanguageCode)) {
        MaterialTheme {
            AppContent(
                updateLanguage = { newLanguageCode ->
                    currentLanguageCode = newLanguageCode
                    localeVersion++ // increment to force recompose
                },
                localeVersion = localeVersion
            )
        }
    }
}

@Composable
fun AppContent(
    updateLanguage: (String) -> Unit,
    localeVersion: Int // <- new parameter
) {
    var currentScreen by remember { mutableStateOf("start") }

    var selectedQuizModes by remember { mutableStateOf(setOf("Creams & Emulsions")) }
    var selectedGameMode by remember { mutableStateOf(GameMode.EXCIPIENT_SPEEDRUN) }
    var selectedQuestionType by remember { mutableStateOf(PropertyType.NAME) }
    var selectedAnswerType by remember { mutableStateOf(PropertyType.STRUCTURE) }
    var selectedExcipient by remember { mutableStateOf<Excipient?>(null) }
    val encyclopediaListState = rememberLazyListState()
    var encyclopediaSearchText by remember { mutableStateOf("") }
    var encyclopediaSelectedFunction by remember { mutableStateOf("All Functions") }
    var specialModeId by remember { mutableStateOf<String?>(null) }
    var specialModeScore by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        SoundManager.playMusic(MusicType.MENU)
    }

    val screenContent: @Composable () -> Unit = {
        when (currentScreen) {
            "start" -> {
                SoundManager.playMusic(MusicType.MENU)
                ExcipientGameStartScreen(
                    selectedQuizModes = selectedQuizModes,
                    questionType = selectedQuestionType,
                    answerType = selectedAnswerType,
                    onQuestionTypeChange = { selectedQuestionType = it },
                    onAnswerTypeChange = { selectedAnswerType = it },
                    onStartChallenge = { currentScreen = "mode_selection" },
                    onShowOptions = { currentScreen = "options" },
                    onShowAchievements = { currentScreen = "achievements" },
                    onShowEncyclopedia = { currentScreen = "encyclopedia" },
                    onShowProgression = { currentScreen = "progression" },
                    onShowSettings = { currentScreen = "settings" }
                )
            }

            // --- SETTINGS SCREEN ---
            "settings" -> key(localeVersion) { // <-- wrap in key to force recomposition
                SettingsScreen(
                    onBack = { currentScreen = "start" },
                    onShowTutorial = { currentScreen = "tutorial" },
                    onShowCredits = { currentScreen = "credits" },
                    updateLanguage = updateLanguage
                )
            }

            "tutorial" -> TutorialScreen(onComplete = { currentScreen = "settings" })
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
            "options" -> OptionsScreen(
                availableModes = quizModes,
                initialSelection = selectedQuizModes,
                onSave = { newModes ->
                    selectedQuizModes = newModes
                    currentScreen = "start"
                },
                onBack = { currentScreen = "start" },
                onShowSpecialModes = { currentScreen = "special_modes" }
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
            "lanette_lingering", "cellulose_connoisseur", "emulsion_types", "stunning_stability" -> {
                SoundManager.playMusic(MusicType.SURVIVAL)
                when (currentScreen) {
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
                }
            }
            "special_mode_result" -> specialModeId?.let {
                SpecialModeResultScreen(
                    modeId = it,
                    score = specialModeScore,
                    onBack = { currentScreen = "special_modes" }
                )
            }
            "excipient_detail" -> selectedExcipient?.let {
                ExcipientDetailScreen(excipient = it, onBack = { currentScreen = "encyclopedia" })
            }
            "game" -> {
                val musicType = when (selectedGameMode) {
                    GameMode.EXCIPIENT_SPEEDRUN -> MusicType.EXCIPIENT_SPEEDRUN
                    GameMode.SURVIVAL -> MusicType.SURVIVAL
                }
                SoundManager.playMusic(musicType)
                ExcipientGameScreen(
                    gameMode = selectedGameMode,
                    questionType = selectedQuestionType,
                    answerType = selectedAnswerType,
                    quizModes = selectedQuizModes,
                    onGameOver = { currentScreen = "start" },
                    onNewAchievements = {},
                    onTierUnlocked = { _, _ -> }
                )
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.White, Color(0xFFE3F2FD))
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

            // The content for the current screen
            screenContent()

            // Debug text to verify current language
//            val debugLanguageCode = LocalLocale.current.language
//            Text(
//                text = "DEBUG: UI LANG IS '$debugLanguageCode'",
//                color = Color.Red,
//                modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
//            )
        }
    }
}
