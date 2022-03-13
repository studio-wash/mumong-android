package com.studiowash.mumong.singleton

import com.studiowash.mumong.common.model.RecordingItem

object MusicPlayer {
    var isPlaying = false
    var currentMusic: RecordingItem? = null
        set(value) {
            field = value
            onMusicChangeListeners.forEach {
                it.onMusicChanged(value)
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