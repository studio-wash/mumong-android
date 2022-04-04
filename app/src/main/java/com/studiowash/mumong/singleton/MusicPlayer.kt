package com.studiowash.mumong.singleton

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.studiowash.mumong.domain.common.RecordingItem


object MusicPlayer {
    private val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

    private var isPlaying = false

    var currentMusic: RecordingItem? = null
        set(value) {
            field = value
            onMusicChangedInternal(value)
            onMusicChangeListeners.forEach {
                it.onMusicChanged(value)
            }
        }

    fun start() {
        isPlaying = true
        if (mediaPlayer.isPlaying.not() && currentMusic != null) {
            mediaPlayer.setOnPreparedListener { it.start() }
        }
    }

    fun pause() {
        isPlaying = false
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
    }

    private fun onMusicChangedInternal(recording: RecordingItem?) {
        if (recording != null) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource("https://download.samplelib.com/mp3/sample-15s.mp3")
            mediaPlayer.prepareAsync()
        }
    }

    private val onMusicChangeListeners = mutableListOf<MusicChangeListener>()

    fun addOnMusicChangeListener(listener: MusicChangeListener) =
        onMusicChangeListeners.add(listener)

    fun removeOnMusicChangeListener(listener: MusicChangeListener) =
        onMusicChangeListeners.remove(listener)
}

fun interface MusicChangeListener {
    fun onMusicChanged(src: RecordingItem?)
}