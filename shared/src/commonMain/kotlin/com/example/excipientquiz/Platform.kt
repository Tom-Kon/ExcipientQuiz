package com.example.excipientquiz

expect fun getPlatformName(): String

expect fun setLocale(languageCode: String)

expect fun resetAllUserData()

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
}
