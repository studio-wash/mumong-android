package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.domain.model.common.RecordingItem
import com.studiowash.mumong.databinding.ItemRecordingBinding
import com.studiowash.mumong.module.sound.MusicPlayService

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

    var item: RecordingItem? = null
        set(value) {
            field = value
            binding.item = item
        }

    var onPlayingStatusChanged: ((isPlaying: Boolean) -> Unit)? = null

    init {
        binding.playPauseImageView.setOnClickListener {
            isPlaying = isPlaying.not()
            onPlayingStatusChanged?.invoke(isPlaying)

            if (MusicPlayService.currentMusic != item)
                MusicPlayService.currentMusic = item

            if (isPlaying)
                MusicPlayService.start()
            else
                MusicPlayService.pause()
        }
    }
}