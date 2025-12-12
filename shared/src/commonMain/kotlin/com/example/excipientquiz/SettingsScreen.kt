package com.example.excipientquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.common_back
import excipientquiz.shared.generated.resources.settings_button_credits
import excipientquiz.shared.generated.resources.settings_language
import excipientquiz.shared.generated.resources.settings_language_de
import excipientquiz.shared.generated.resources.settings_language_en
import excipientquiz.shared.generated.resources.settings_language_fr
import excipientquiz.shared.generated.resources.settings_language_nl
import excipientquiz.shared.generated.resources.settings_music
import excipientquiz.shared.generated.resources.settings_sfx
import excipientquiz.shared.generated.resources.settings_show_tutorial
import excipientquiz.shared.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private data class LanguageOption(val code: String, val nameRes: StringResource)

private val languages = listOf(
    LanguageOption("en", Res.string.settings_language_en),
    LanguageOption("nl", Res.string.settings_language_nl),
    LanguageOption("fr", Res.string.settings_language_fr),
    LanguageOption("de", Res.string.settings_language_de)
)

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onShowTutorial: () -> Unit,
    onShowCredits: () -> Unit,
    updateLanguage: (String) -> Unit // <-- 1. ADD THIS PARAMETER
) {
    val musicEnabled = remember { mutableStateOf(SettingsManager.isMusicEnabled()) }
    val sfxEnabled = remember { mutableStateOf(SettingsManager.isSfxEnabled()) }
    var languageMenuExpanded by remember { mutableStateOf(false) }

    // This gets the current language from the CompositionLocal, which is provided in App.kt
    val currentLanguageCode = LocalLocale.current.language

    var showResetDialog by remember { mutableStateOf(false) }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Progression?") },
            text = { Text("Are you sure you want to reset all your progress? This will clear all achievements and progression.") },
            confirmButton = {
                Button(
                    onClick = {
                        resetAllUserData()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                Button(onClick = { showResetDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(Res.string.settings_title), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(48.dp))

        SettingSwitch(label = stringResource(Res.string.settings_music), isChecked = musicEnabled.value) {
            musicEnabled.value = it
            SettingsManager.setMusicEnabled(it)
            if (it) SoundManager.resumeMusic() else SoundManager.pauseBackgroundMusic()
        }
        Spacer(modifier = Modifier.height(24.dp))
        SettingSwitch(label = stringResource(Res.string.settings_sfx), isChecked = sfxEnabled.value) {
            sfxEnabled.value = it
            SettingsManager.setSfxEnabled(it)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(Res.string.settings_language), style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                TextButton(onClick = { languageMenuExpanded = true }) {
                    val currentLanguage = languages.find { it.code == currentLanguageCode } ?: languages.first()
                    Text(stringResource(currentLanguage.nameRes))
                }
                DropdownMenu(
                    expanded = languageMenuExpanded,
                    onDismissRequest = { languageMenuExpanded = false }
                ) {
                    languages.forEach { lang ->
                        DropdownMenuItem(
                            text = { Text(stringResource(lang.nameRes)) },
                            onClick = {
                                // --- 2. THIS IS THE LOGIC CHANGE ---
                                val newLanguage = lang.code
                                // First, save the setting so it persists
                                SettingsManager.setLanguage(newLanguage)
                                // Second, call the function to update the UI state in App.kt
                                updateLanguage(newLanguage)
                                // Third, trigger the platform-specific action (like Activity recreation)
                                setLocale(newLanguage)

                                languageMenuExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onShowTutorial) { Text(stringResource(Res.string.settings_show_tutorial)) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onShowCredits) { Text(stringResource(Res.string.settings_button_credits)) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showResetDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Reset Progression")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onBack) { Text(stringResource(Res.string.common_back)) }
    }
}

@Composable
private fun SettingSwitch(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}
