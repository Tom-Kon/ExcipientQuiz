package com.example.excipientquiz

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.browser.document
import kotlinx.browser.localStorage
import org.w3c.dom.Audio
import excipientquiz.shared.generated.resources.Res
import excipientquiz.shared.generated.resources.settings_language_en

actual fun getPlatformName(): String {
    return "Web"
}

actual fun setLocale(languageCode: String) {
    LanguageManager.applyLanguage(languageCode)
}

actual object LanguageManager {
    private val languages = listOf(
        LanguageOption("en", Res.string.settings_language_en, "🇬🇧")
    )

    actual fun getLanguages(): List<LanguageOption> = languages

    actual fun getCurrentLanguageCode(): String {
        return "en"
    }

    actual fun applyLanguage(languageCode: String) {
        // Force English for now on Web
        SettingsManager.setLanguage("en")
        document.documentElement?.setAttribute("lang", "en")
    }
}

actual fun resetAllUserData() {
    localStorage.clear()
}

actual object SettingsManager {
    private const val LANGUAGE_KEY = "language_pref"
    private val settings: Settings by lazy { createSettings() }

    actual fun isMusicEnabled(): Boolean {
        return settings.getBoolean("music_enabled", true)
    }

    actual fun setMusicEnabled(enabled: Boolean) {
        settings["music_enabled"] = enabled
    }

    actual fun isSfxEnabled(): Boolean {
        return settings.getBoolean("sfx_enabled", true)
    }

    actual fun setSfxEnabled(enabled: Boolean) {
        settings["sfx_enabled"] = enabled
    }

    actual fun getLanguage(): String {
        // Force English for Web
        return "en"
    }

    actual fun setLanguage(language: String) {
        localStorage.setItem("language_pref", "en")
        settings[LANGUAGE_KEY] = "en"
    }
}

actual object SoundManager {
    private val musicPlayers = mutableMapOf<MusicType, Audio>()
    private val soundEffectPlayers = mutableMapOf<SoundEffect, Audio>()
    private var currentPlayer: Audio? = null
    private var currentMusicType: MusicType? = null

    private val musicMap = mapOf(
        MusicType.MENU to "sounds/general.mp3",
        MusicType.EXCIPIENT_SPEEDRUN to "sounds/timedmode.mp3",
        MusicType.SURVIVAL to "sounds/survivalmode.mp3"
    )

    private val soundMap = mapOf(
        SoundEffect.SUCCESS to "sounds/success.mp3",
        SoundEffect.FAIL to "sounds/fail.mp3",
        SoundEffect.WHOOSH to "sounds/whoosh.wav",
        SoundEffect.GAME_OVER to "sounds/gameover.mp3",
        SoundEffect.SUCCESS_END to "sounds/succesend.mp3",
        SoundEffect.ACHIEVEMENT to "sounds/achievement.mp3"
    )

    private fun getMusicPlayer(musicType: MusicType): Audio {
        return musicPlayers.getOrPut(musicType) {
            val musicPath = musicMap[musicType]!!
            Audio(musicPath).apply {
                loop = true
                volume = 0.1
            }
        }
    }

    private fun getSoundPlayer(soundEffect: SoundEffect): Audio {
        return soundEffectPlayers.getOrPut(soundEffect) {
            val soundPath = soundMap[soundEffect]!!
            Audio(soundPath).apply {
                volume = 0.1
            }
        }
    }

    actual fun playMusic(musicType: MusicType) {
        if (!SettingsManager.isMusicEnabled()) return
        if (currentMusicType == musicType && currentPlayer?.paused == false) return
        if (currentMusicType != musicType) currentPlayer?.pause()

        val newPlayer = getMusicPlayer(musicType)
        newPlayer.currentTime = 0.0
        newPlayer.play()?.catch {
            console.warn("Background music playback failed.", it)
        }

        currentPlayer = newPlayer
        currentMusicType = musicType
    }

    actual fun resumeMusic() {
        if (!SettingsManager.isMusicEnabled()) return
        val player = currentPlayer ?: getMusicPlayer(MusicType.MENU)
        player.play()?.catch {
            console.warn("Background music playback failed:", it)
        }
        if (currentPlayer == null) {
            currentPlayer = player
            currentMusicType = MusicType.MENU
        }
    }

    actual fun pauseBackgroundMusic() {
        currentPlayer?.pause()
    }

    actual fun playSound(soundEffect: SoundEffect) {
        if (!SettingsManager.isSfxEnabled()) return
        val player = getSoundPlayer(soundEffect)
        player.currentTime = 0.0
        player.play()?.catch {
            console.warn("Sound effect playback failed:", it)
        }
    }

    actual fun stopBackgroundMusic() {
        currentPlayer?.pause()
        currentPlayer = null
        currentMusicType = null
    }

    actual fun releaseSoundPool() {}
}
