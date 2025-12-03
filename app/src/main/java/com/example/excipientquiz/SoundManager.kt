package com.example.excipientquiz

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

object SoundManager {

    enum class MusicType {
        MENU,
        EXCIPIENT_SPEEDRUN,
        SURVIVAL
    }

    private var menuPlayer: MediaPlayer? = null
    private var excipientSpeedrunPlayer: MediaPlayer? = null
    private var survivalPlayer: MediaPlayer? = null

    private var currentMusicType: MusicType? = null
    private var fadeJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    private const val FADE_DURATION = 500L
    private const val TARGET_VOLUME = 0.1f
    private const val VOLUME_STEPS = 20

    private var audioManager: AudioManager? = null
    private var focusRequest: AudioFocusRequest? = null
    private var hasFocus = false
    private var wasFocusLostTransiently = false
    private var contextRef: WeakReference<Context>? = null

    private val focusListener = AudioManager.OnAudioFocusChangeListener { focusChange ->
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS -> {
                pauseBackgroundMusic(andAbandonFocus = true)
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT, AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                pauseBackgroundMusic()
                wasFocusLostTransiently = true
            }
            AudioManager.AUDIOFOCUS_GAIN -> {
                contextRef?.get()?.let {
                    if (wasFocusLostTransiently) resumeMusic(it)
                }
            }
        }
    }

    private fun getPlayer(type: MusicType?): MediaPlayer? {
        return when (type) {
            MusicType.MENU -> menuPlayer
            MusicType.EXCIPIENT_SPEEDRUN -> excipientSpeedrunPlayer
            MusicType.SURVIVAL -> survivalPlayer
            null -> null
        }
    }

    private fun requestAudioFocus(context: Context): Boolean {
        contextRef = WeakReference(context)
        if (hasFocus) return true
        if (audioManager == null) {
            audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        }

        val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            focusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(attributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(focusListener)
                .build()
            audioManager?.requestAudioFocus(focusRequest!!)
        } else {
            @Suppress("DEPRECATION")
            audioManager?.requestAudioFocus(focusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        }

        hasFocus = (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        return hasFocus
    }

    fun playMusic(context: Context, type: MusicType) {
        if (!SettingsManager.isMusicEnabled(context)) return
        if (currentMusicType == type && (getPlayer(type)?.isPlaying == true || fadeJob?.isActive == true)) return
        if (!requestAudioFocus(context)) return

        fadeJob?.cancel()

        val oldPlayer = getPlayer(currentMusicType)
        currentMusicType = type
        var newPlayer = getPlayer(type)

        if (newPlayer == null) {
            val musicResId = when (type) {
                MusicType.MENU -> R.raw.general
                MusicType.EXCIPIENT_SPEEDRUN -> R.raw.timedmode
                MusicType.SURVIVAL -> R.raw.survivalmode
            }
            newPlayer = MediaPlayer.create(context, musicResId).apply { isLooping = true }
            when (type) {
                MusicType.MENU -> menuPlayer = newPlayer
                MusicType.EXCIPIENT_SPEEDRUN -> excipientSpeedrunPlayer = newPlayer
                MusicType.SURVIVAL -> survivalPlayer = newPlayer
            }
        }

        fadeJob = scope.launch {
            if (oldPlayer != null && oldPlayer.isPlaying) {
                launch {
                    for (i in VOLUME_STEPS downTo 0) {
                        val volume = (i.toFloat() / VOLUME_STEPS) * TARGET_VOLUME
                        try { oldPlayer.setVolume(volume, volume) } catch (e: Exception) { break }
                        delay(FADE_DURATION / VOLUME_STEPS)
                    }
                    try { oldPlayer.pause() } catch (e: Exception) { /* Ignored */ }
                }
            }

            launch {
                newPlayer?.setVolume(0f, 0f)
                try { if (newPlayer?.isPlaying == false) newPlayer.start() } catch (e: Exception) { return@launch }
                for (i in 0..VOLUME_STEPS) {
                    val volume = (i.toFloat() / VOLUME_STEPS) * TARGET_VOLUME
                    try { newPlayer?.setVolume(volume, volume) } catch (e: Exception) { break }
                    delay(FADE_DURATION / VOLUME_STEPS)
                }
            }
        }
    }

    fun pauseBackgroundMusic(andAbandonFocus: Boolean = false) {
        fadeJob?.cancel()
        getPlayer(currentMusicType)?.pause()
        if (andAbandonFocus) {
            abandonAudioFocus()
        }
    }

    fun resumeMusic(context: Context) {
        if (!SettingsManager.isMusicEnabled(context)) return
        val player = getPlayer(currentMusicType)
        if (player != null && !player.isPlaying) {
            if (requestAudioFocus(context)) {
                player.start()
            }
        } else if (player == null) {
            playMusic(context, MusicType.MENU)
        }
    }

    fun stopBackgroundMusic() {
        fadeJob?.cancel()
        menuPlayer?.release(); menuPlayer = null
        excipientSpeedrunPlayer?.release(); excipientSpeedrunPlayer = null
        survivalPlayer?.release(); survivalPlayer = null
        currentMusicType = null
        abandonAudioFocus()
    }

    fun abandonAudioFocus() { // Changed from private fun to fun
        if (audioManager == null) return
        focusRequest?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                audioManager?.abandonAudioFocusRequest(it)
            }
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            @Suppress("DEPRECATION")
            audioManager?.abandonAudioFocus(focusListener)
        }
        hasFocus = false
    }

    fun playSound(context: Context, soundId: Int) {
        if (!SettingsManager.isSfxEnabled(context)) return
        MediaPlayer.create(context, soundId).apply {
            setVolume(0.5f, 0.5f)
            setOnCompletionListener { it.release() }
            start()
        }
    }
}
