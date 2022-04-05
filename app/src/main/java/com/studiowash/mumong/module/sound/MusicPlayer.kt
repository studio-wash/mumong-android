package com.studiowash.mumong.module.sound

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.studiowash.mumong.domain.model.common.RecordingItem
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
            musicTimer?.cancel()
        }
        mediaPlayer.setOnPreparedListener { player ->
            player.start()
            musicTimer = Timer()
            musicTimer?.schedule(MusicTimerTask(), 0, SECOND_IN_MILLI.toLong())
            musicPlayerListeners.forEach {
                it.onMusicPrepared(player.duration)
            }
        }
    }

    private var isPlaying = false

    var currentMusic: RecordingItem? = null
        set(value) {
            field = value
            onMusicChangedInternal(value)
            musicPlayerListeners.forEach {
                it.onMusicChanged(value)
            }
        }

    fun start() {
        isPlaying = true
    }

    fun pause() {
        isPlaying = false
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            musicTimer?.cancel()
        }
    }

    fun seekPercent(progress: Int) {
        mediaPlayer.seekTo((mediaPlayer.duration * progress.toFloat()/100).toInt())
    }

    private fun onMusicChangedInternal(recording: RecordingItem?) {
        if (recording != null) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource("https://download.samplelib.com/mp3/sample-15s.mp3")
            mediaPlayer.prepareAsync()
        }
    }

    private val musicPlayerListeners = mutableListOf<MusicChangeListener>()

    fun addOnMusicChangeListener(listener: MusicChangeListener) =
        musicPlayerListeners.add(listener)

    fun removeOnMusicChangeListener(listener: MusicChangeListener) =
        musicPlayerListeners.remove(listener)
}

interface MusicChangeListener {
    fun onMusicChanged(recording: RecordingItem?)
    fun onMusicPrepared(durationMilli: Int)
    fun onUpdatePosition(currentMilli: Int)
}