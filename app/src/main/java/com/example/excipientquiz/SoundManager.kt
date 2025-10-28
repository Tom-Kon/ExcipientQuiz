package com.example.excipientquiz

import android.content.Context
import android.media.MediaPlayer

object SoundManager {

    private var backgroundMusicPlayer: MediaPlayer? = null

    fun playBackgroundMusic(context: Context) {
        if (!SettingsManager.isMusicEnabled(context)) return

        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = MediaPlayer.create(context, R.raw.background).apply {
                isLooping = true
                setVolume(0.2f, 0.2f)
                start()
            }
        } else {
            if (!backgroundMusicPlayer!!.isPlaying) {
                backgroundMusicPlayer!!.start()
            }
        }
    }

    fun pauseBackgroundMusic() {
        if (backgroundMusicPlayer?.isPlaying == true) {
            backgroundMusicPlayer?.pause()
        }
    }

    fun stopBackgroundMusic() {
        backgroundMusicPlayer?.stop()
        backgroundMusicPlayer?.release()
        backgroundMusicPlayer = null
    }

    fun playSound(context: Context, soundId: Int) {
        if (!SettingsManager.isSfxEnabled(context)) return
        
        MediaPlayer.create(context, soundId).apply {
            setOnCompletionListener { it.release() }
            start()
        }
    }
}
