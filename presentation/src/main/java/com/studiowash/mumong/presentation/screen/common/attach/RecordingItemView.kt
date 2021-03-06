package com.studiowash.mumong.presentation.screen.common.attach

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.presentation.databinding.ItemRecordingBinding
import com.studiowash.mumong.presentation.screen.common.player.MusicPlayer

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

    var item: com.studiowash.mumong.domain.common.entity.RecordingEntity? = null
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