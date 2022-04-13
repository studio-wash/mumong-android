package com.studiowash.mumong.presentation.module.sound

import android.media.AudioAttributes
import android.media.MediaPlayer
import java.util.*

object MusicPlayer {
    const val SECOND_IN_MILLI = 1000
    private var musicTimer : Timer? = null
    class MusicTimerTask : TimerTask() {
        override fun run() {
            if (mediaPlayer.isPlaying) {
                musicPlayerListeners.forEach {
                    it.onUpdatePosition(mediaPlayer.currentPosition)
                }
            }
        }
    }

    private val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

    init {
        mediaPlayer.setOnCompletionListener {
            musicPlayerListeners.forEach {
                it.onUpdatePosition(mediaPlayer.currentPosition)
            }
            stopTimer()
        }
        mediaPlayer.setOnPreparedListener { player ->
            player.start()
            startTimer()
            musicPlayerListeners.forEach {
                it.onMusicPrepared(player.duration)
            }
        }
    }

    private var musicPlayState = MusicPlayState.Stop
        set(value) {
            musicPlayerListeners.forEach {
                it.onMusicPlayStateChanged(value)
            }
        }

    var currentMusic: com.studiowash.mumong.domain.common.entity.RecordingEntity? = null
        set(value) {
            field = value
            onMusicChangedInternal(value)
            musicPlayerListeners.forEach {
                it.onMusicChanged(value)
            }
        }

    fun start() {
        startTimer()
        mediaPlayer.start()
        musicPlayState = MusicPlayState.Play
    }

    fun pause() {
        stopTimer()
        musicPlayState = MusicPlayState.Pause
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun stop() {
        musicPlayState = MusicPlayState.Stop
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            stopTimer()
        }
    }

    fun seekPercent(progress: Int) {
        mediaPlayer.seekTo((mediaPlayer.duration * progress.toFloat()/100).toInt())
    }

    private fun onMusicChangedInternal(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?) {
        if (recording != null) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource("https://download.samplelib.com/mp3/sample-15s.mp3")
            mediaPlayer.prepareAsync()
        }
    }

    private fun startTimer() {
        musicTimer = Timer()
        musicTimer?.schedule(MusicTimerTask(), 0, SECOND_IN_MILLI.toLong())
    }

    private fun stopTimer() =
        musicTimer?.cancel()

    private val musicPlayerListeners = mutableListOf<MusicChangeListener>()

    fun addOnMusicChangeListener(listener: MusicChangeListener) =
        musicPlayerListeners.add(listener)

    fun removeOnMusicChangeListener(listener: MusicChangeListener) =
        musicPlayerListeners.remove(listener)

    enum class MusicPlayState{ Play, Pause, Stop }
}

interface MusicChangeListener {
    fun onMusicChanged(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?)
    fun onMusicPrepared(durationMilli: Int)
    fun onUpdatePosition(currentMilli: Int)
    fun onMusicPlayStateChanged(state: MusicPlayer.MusicPlayState)
}

