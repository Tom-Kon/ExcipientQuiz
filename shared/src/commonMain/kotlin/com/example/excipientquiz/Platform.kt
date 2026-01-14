package com.example.excipientquiz

import org.jetbrains.compose.resources.StringResource

expect fun getPlatformName(): String

expect fun setLocale(languageCode: String)

expect fun resetAllUserData()

data class LanguageOption(
    val code: String,
    val nameRes: StringResource,
    val flagEmoji: String
)

expect object LanguageManager {
    fun getLanguages(): List<LanguageOption>
    fun getCurrentLanguageCode(): String
    fun applyLanguage(languageCode: String)
}

expect object SettingsManager {
    fun isMusicEnabled(): Boolean
    fun setMusicEnabled(enabled: Boolean)
    fun isSfxEnabled(): Boolean
    fun setSfxEnabled(enabled: Boolean)
    fun getLanguage(): String
    fun setLanguage(language: String)
}

expect object SoundManager {
    fun resumeMusic()
    fun pauseBackgroundMusic()
    fun playSound(soundEffect: SoundEffect)
    fun playMusic(musicType: MusicType)
    fun stopBackgroundMusic()
    fun releaseSoundPool()
}
