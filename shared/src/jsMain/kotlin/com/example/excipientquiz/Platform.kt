package com.example.excipientquiz

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.w3c.dom.Audio


actual fun getPlatformName(): String {
    return "Web"
}

/**
 * This function's ONLY responsibilities are to save the language setting
 * and update the web page's 'lang' attribute.
 * It no longer triggers recomposition. That is handled by the App.kt state.
 */
actual fun setLocale(languageCode: String) {
    // 1. Save the language setting so it persists.
    SettingsManager.setLanguage(languageCode)
    // 2. Set the 'lang' attribute on the HTML tag for accessibility and SEO.
    document.documentElement?.setAttribute("lang", languageCode)
    // 3. (Optional but recommended) Reload the page to ensure all resources are re-evaluated
    //    with the new language context, especially for the initial load logic in main.js.kt.
    window.location.reload()
}

actual fun resetAllUserData() {
    localStorage.clear()
}

// Your SettingsManager and SoundManager objects remain unchanged as they are correct.

actual object SettingsManager {

    private const val FIRST_RUN_KEY = "has_run_before"

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
        val hasRunBefore = settings.getBoolean(FIRST_RUN_KEY, false)

        // FIRST LAUNCH → force English and mark as initialized
        if (!hasRunBefore) {
            settings[FIRST_RUN_KEY] = true
            settings["language"] = "en"
            return "en"
        }

        // Afterwards → always return saved language if valid
        val savedLanguage = settings.getString("language", "")
        if (savedLanguage.isNotBlank() && savedLanguage in supportedLanguages) {
            return savedLanguage
        }

        // Absolute fallback (should never be reached)
        return "en"
    }


    actual fun setLanguage(language: String) {
        settings["language"] = language
    }

    val supportedLanguages = listOf("en", "de", "fr", "nl")
}

actual object SoundManager {
    // Cache players to avoid re-creating them. This is more efficient and helps with state.
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

    // Helper to get a music player from the cache or create it
    private fun getMusicPlayer(musicType: MusicType): Audio {
        return musicPlayers.getOrPut(musicType) {
            val musicPath = musicMap[musicType]!!
            Audio(musicPath).apply {
                loop = true
                volume = 0.1
            }
        }
    }

    // Helper to get a sound player from the cache or create it
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

        // If the same music is requested and it's not paused, do nothing.
        if (currentMusicType == musicType && currentPlayer?.paused == false) {
            return
        }

        // Pause the previous track if it's different music
        if (currentMusicType != musicType) {
            currentPlayer?.pause()
        }

        val newPlayer = getMusicPlayer(musicType)

        // Always start from the beginning when switching tracks
        newPlayer.currentTime = 0.0

        newPlayer.play()?.catch {
            console.warn("Background music playback failed. This is often due to browser autoplay policies. Music should start after a user interaction.", it)
        }

        currentPlayer = newPlayer
        currentMusicType = musicType
    }

    actual fun resumeMusic() {
        if (!SettingsManager.isMusicEnabled()) return

        // If there's no current player, default to menu music.
        val player = currentPlayer ?: getMusicPlayer(MusicType.MENU)

        player.play()?.catch {
            console.warn("Background music playback failed:", it)
        }

        // Make sure state is consistent
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
        player.currentTime = 0.0 // Reset to the start so it can be replayed
        player.play()?.catch {
            console.warn("Sound effect playback failed:", it)
        }
    }

    actual fun stopBackgroundMusic() {
        currentPlayer?.pause()
        currentPlayer = null
        currentMusicType = null
    }
}
