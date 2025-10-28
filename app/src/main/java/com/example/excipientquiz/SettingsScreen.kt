package com.example.excipientquiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(onBack: () -> Unit, onShowTutorial: () -> Unit) {
    val context = LocalContext.current

    val musicEnabled = remember { mutableStateOf(SettingsManager.isMusicEnabled(context)) }
    val sfxEnabled = remember { mutableStateOf(SettingsManager.isSfxEnabled(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(48.dp))

        SettingSwitch(label = "Background Music", isChecked = musicEnabled.value) {
            musicEnabled.value = it
            SettingsManager.setMusicEnabled(context, it)
            if (it) {
                SoundManager.playBackgroundMusic(context)
            } else {
                SoundManager.pauseBackgroundMusic()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        SettingSwitch(label = "Sound Effects", isChecked = sfxEnabled.value) {
            sfxEnabled.value = it
            SettingsManager.setSfxEnabled(context, it)
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = onShowTutorial) {
            Text("Show Tutorial Again")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onBack) {
            Text("Back")
        }
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
