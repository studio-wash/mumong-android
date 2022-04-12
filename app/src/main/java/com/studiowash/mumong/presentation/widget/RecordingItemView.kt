package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.domain.common.entity.RecordingEntity
import com.studiowash.mumong.databinding.ItemRecordingBinding
import com.studiowash.mumong.module.sound.MusicPlayer

class RecordingItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ItemRecordingBinding.inflate(LayoutInflater.from(context), this, true)

    var isPlaying: Boolean = false
        set(value) {
            field = value
            binding.isPlaying = value
        }

    var item: RecordingEntity? = null
        set(value) {
            field = value
            binding.item = item
        }

    var onPlayingStatusChanged: ((isPlaying: Boolean) -> Unit)? = null

    init {
        binding.playPauseImageView.setOnClickListener {
            isPlaying = isPlaying.not()
            onPlayingStatusChanged?.invoke(isPlaying)

            if (MusicPlayer.currentMusic != item)
                MusicPlayer.currentMusic = item

            if (isPlaying)
                MusicPlayer.start()
            else
                MusicPlayer.pause()
        }
    }
}