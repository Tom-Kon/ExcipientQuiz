package com.example.excipientquiz

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Handler
import android.os.HandlerThread
import com.example.excipientquiz.shared.R
import java.util.concurrent.ConcurrentHashMap

actual object SoundManager {

    private val musicThread = HandlerThread("ExcipientMusicThread").apply { start() }
    private val musicHandler = Handler(musicThread.looper)

    private var backgroundMusicPlayer: MediaPlayer? = null
    private var currentMusicType: MusicType? = null

    private var soundPool: SoundPool? = null
    private val soundMap = ConcurrentHashMap<SoundEffect, Int>()
    private val loadedSounds = ConcurrentHashMap.newKeySet<Int>()

    // Pre-initialize the pool so sounds are ready before the user even reaches the quiz
    private fun initSoundPool() {
        if (soundPool != null) return

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val pool = SoundPool.Builder()
            .setMaxStreams(15) // Increased limit for overlapping sounds
            .setAudioAttributes(attributes)
            .build()

        pool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0) loadedSounds.add(sampleId)
        }

        val context = AppContext.context
        // Load all sounds into memory immediately
        soundMap[SoundEffect.SUCCESS] = pool.load(context, R.raw.success, 1)
        soundMap[SoundEffect.FAIL] = pool.load(context, R.raw.fail, 1)
        soundMap[SoundEffect.WHOOSH] = pool.load(context, R.raw.whoosh, 1)
        soundMap[SoundEffect.GAME_OVER] = pool.load(context, R.raw.gameover, 1)
        soundMap[SoundEffect.SUCCESS_END] = pool.load(context, R.raw.succesend, 1)
        soundMap[SoundEffect.ACHIEVEMENT] = pool.load(context, R.raw.achievement, 1)
        
        soundPool = pool
    }

    actual fun playMusic(musicType: MusicType) {
        // Ensure SFX are also being initialized in the background when music starts
        if (soundPool == null) musicHandler.post { initSoundPool() }

        musicHandler.post {
            if (!SettingsManager.isMusicEnabled()) return@post
            if (currentMusicType == musicType && backgroundMusicPlayer?.isPlaying == true) return@post

            try {
                backgroundMusicPlayer?.stop()
                backgroundMusicPlayer?.release()
            } catch (e: Exception) {}

            currentMusicType = musicType
            val resId = when (musicType) {
                MusicType.MENU -> R.raw.general
                MusicType.EXCIPIENT_SPEEDRUN -> R.raw.timedmode
                MusicType.SURVIVAL -> R.raw.survivalmode
            }

            try {
                backgroundMusicPlayer = MediaPlayer.create(AppContext.context, resId)?.apply {
                    isLooping = true
                    setVolume(0.15f, 0.15f)
                    start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    actual fun resumeMusic() {
        musicHandler.post {
            if (!SettingsManager.isMusicEnabled()) return@post
            try {
                if (backgroundMusicPlayer?.isPlaying == false) backgroundMusicPlayer?.start()
            } catch (e: Exception) {}
        }
    }

    actual fun pauseBackgroundMusic() {
        musicHandler.post {
            try {
                if (backgroundMusicPlayer?.isPlaying == true) backgroundMusicPlayer?.pause()
            } catch (e: Exception) {}
        }
    }

    actual fun stopBackgroundMusic() {
        musicHandler.post {
            try {
                backgroundMusicPlayer?.stop()
                backgroundMusicPlayer?.release()
            } catch (e: Exception) {}
            backgroundMusicPlayer = null
            currentMusicType = null
        }
    }

    actual fun playSound(soundEffect: SoundEffect) {
        if (!SettingsManager.isSfxEnabled()) return
        
        val pool = soundPool ?: run {
            // Fallback initialization if something went wrong
            musicHandler.post { initSoundPool() }
            return
        }

        val soundId = soundMap[soundEffect]
        if (soundId != null && loadedSounds.contains(soundId)) {
            // Play with higher priority (2) for Whoosh to ensure it cuts through
            val priority = if (soundEffect == SoundEffect.WHOOSH) 2 else 1
            pool.play(soundId, 0.4f, 0.4f, priority, 0, 1f)
        }
    }

    actual fun releaseSoundPool() {
        soundPool?.release()
        soundPool = null
        soundMap.clear()
        loadedSounds.clear()
    }
}
