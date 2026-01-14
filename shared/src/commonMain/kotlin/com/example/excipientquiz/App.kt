package com.example.excipientquiz

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.runtime.staticCompositionLocalOf

// 1. Definition of your custom LocalLocale for standard Compose components
val LocalAppLanguage = staticCompositionLocalOf { Locale("en") }

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    // 2. Initialize state from the platform's current language code
    var currentLanguageCode by remember { mutableStateOf(LanguageManager.getCurrentLanguageCode()) }

    // 3. Wrap in CompositionLocalProvider
    // 3. Wrap in CompositionLocalProvider
    // 3. Wrap in CompositionLocalProvider
    // 3. Wrap in CompositionLocalProvider
    // 3. Wrap in CompositionLocalProvider
    CompositionLocalProvider(
        // This provides the Locale for standard Compose UI logic (your custom val)
        LocalAppLanguage provides Locale(currentLanguageCode))

        // This is the property provided by the JetBrains library.
        // If it shows red in the IDE, ignore it and run the command in Step 2.
    { MaterialTheme {
            key(currentLanguageCode) {
                AppContent(
                    updateLanguage = { newLanguageCode ->
                        currentLanguageCode = newLanguageCode
                    }
                )
            }
        }
    }
}


@Composable
fun AppContent(
    updateLanguage: (String) -> Unit
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

    // --- STABILIZED MUSIC LOGIC ---
    LaunchedEffect(currentScreen, selectedGameMode) {
        val musicType = when (currentScreen) {
            "game" -> when (selectedGameMode) {
                GameMode.EXCIPIENT_SPEEDRUN -> MusicType.EXCIPIENT_SPEEDRUN
                GameMode.SURVIVAL -> MusicType.SURVIVAL
                GameMode.STUDY -> MusicType.MENU
            }
            "lanette_lingering", "cellulose_connoisseur", "emulsion_types", "stunning_stability" -> MusicType.SURVIVAL
            else -> MusicType.MENU
        }
        SoundManager.playMusic(musicType)
    }

    val screenContent: @Composable () -> Unit = {
        when (currentScreen) {
            "start" -> {
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

            "settings" -> SettingsScreen(
                onBack = { currentScreen = "start" },
                onShowTutorial = { currentScreen = "tutorial" },
                onShowCredits = { currentScreen = "credits" },
                updateLanguage = updateLanguage
            )

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
            screenContent()
        }
    }
}
