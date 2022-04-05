package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.domain.model.common.RecordingItem
import com.studiowash.mumong.databinding.ViewMusicPlayerBinding

class MusicPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewMusicPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    var currentRecording: RecordingItem? = null
        set(value) {
            field = value
            binding.currentRecording = value
        }

    var totalSeconds: Int = 0
        set(value) {
            field = value
            binding.totalTimeSec = value
        }

    var currentSeconds: Int = 0
        set(value) {
            field = value
            binding.currentTimeSec = value
        }
}
