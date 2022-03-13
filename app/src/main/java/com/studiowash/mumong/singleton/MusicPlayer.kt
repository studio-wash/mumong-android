package com.studiowash.mumong.singleton

object MusicPlayer {
    var isPlaying = false
    var currentMusicSrc: String? = null
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
    fun onMusicChanged(src:String?)
}