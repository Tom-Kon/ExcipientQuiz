package com.example.excipientquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Locale

private data class LanguageOption(val code: String, val nameResId: Int, val flagEmoji: String)

private val languages = listOf(
    LanguageOption("en", R.string.settings_language_en, "🇬🇧"),
    LanguageOption("nl", R.string.settings_language_nl, "🇳🇱"),
    LanguageOption("fr", R.string.settings_language_fr, "🇫🇷"),
    LanguageOption("de", R.string.settings_language_de, "🇩🇪")
)

@Composable
fun SettingsScreen(onBack: () -> Unit, onShowTutorial: () -> Unit) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    val musicEnabled = remember { mutableStateOf(SettingsManager.isMusicEnabled(context)) }
    val sfxEnabled = remember { mutableStateOf(SettingsManager.isSfxEnabled(context)) }
    var languageMenuExpanded by remember { mutableStateOf(false) }
    val currentLanguageCode = remember { mutableStateOf(SettingsManager.getLanguage(context)) }

    fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        SettingsManager.setLanguage(context, languageCode)
        activity?.recreate()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.settings_title), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(48.dp))

        SettingSwitch(label = stringResource(id = R.string.settings_music), isChecked = musicEnabled.value) {
            musicEnabled.value = it
            SettingsManager.setMusicEnabled(context, it)
            if (it) SoundManager.playBackgroundMusic(context) else SoundManager.pauseBackgroundMusic()
        }
        Spacer(modifier = Modifier.height(24.dp))
        SettingSwitch(label = stringResource(id = R.string.settings_sfx), isChecked = sfxEnabled.value) {
            sfxEnabled.value = it
            SettingsManager.setSfxEnabled(context, it)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(id = R.string.settings_language), style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                TextButton(onClick = { languageMenuExpanded = true }) {
                    val currentLanguage = languages.find { it.code == currentLanguageCode.value } ?: languages.first()
                    Text("${currentLanguage.flagEmoji} ${stringResource(id = currentLanguage.nameResId)}")
                }
                DropdownMenu(
                    expanded = languageMenuExpanded,
                    onDismissRequest = { languageMenuExpanded = false }
                ) {
                    languages.forEach { lang ->
                        DropdownMenuItem(
                            text = { Text("${lang.flagEmoji} ${stringResource(id = lang.nameResId)}") },
                            onClick = { 
                                setLocale(lang.code)
                                currentLanguageCode.value = lang.code
                                languageMenuExpanded = false 
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onShowTutorial) { Text(stringResource(id = R.string.settings_show_tutorial)) }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onBack) { Text(stringResource(id = R.string.common_back)) }
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
