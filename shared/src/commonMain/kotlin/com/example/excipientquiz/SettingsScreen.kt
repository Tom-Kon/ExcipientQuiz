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
import excipientquiz.shared.generated.resources.settings_music
import excipientquiz.shared.generated.resources.settings_sfx
import excipientquiz.shared.generated.resources.settings_show_tutorial
import excipientquiz.shared.generated.resources.settings_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onShowTutorial: () -> Unit,
    onShowCredits: () -> Unit,
    updateLanguage: (String) -> Unit
) {
    val musicEnabled = remember { mutableStateOf(SettingsManager.isMusicEnabled()) }
    val sfxEnabled = remember { mutableStateOf(SettingsManager.isSfxEnabled()) }
    var languageMenuExpanded by remember { mutableStateOf(false) }

    val availableLanguages = LanguageManager.getLanguages()
    var currentLanguageCode by remember { mutableStateOf(LanguageManager.getCurrentLanguageCode()) }

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
        
        if (availableLanguages.size > 1) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(Res.string.settings_language), style = MaterialTheme.typography.bodyLarge)
                Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                    TextButton(onClick = { languageMenuExpanded = true }) {
                        val currentLanguage = availableLanguages.find { it.code == currentLanguageCode } ?: availableLanguages.first()
                        Text("${currentLanguage.flagEmoji} ${stringResource(currentLanguage.nameRes)}")
                    }
                    DropdownMenu(
                        expanded = languageMenuExpanded,
                        onDismissRequest = { languageMenuExpanded = false }
                    ) {
                        availableLanguages.forEach { lang ->
                            DropdownMenuItem(
                                text = { Text("${lang.flagEmoji} ${stringResource(lang.nameRes)}") },
                                onClick = {
                                    val newLanguage = lang.code
                                    SettingsManager.setLanguage(newLanguage)
                                    currentLanguageCode = newLanguage
                                    updateLanguage(newLanguage)
                                    LanguageManager.applyLanguage(newLanguage)
                                    languageMenuExpanded = false
                                }
                            )
                        }
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
