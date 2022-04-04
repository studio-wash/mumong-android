package com.studiowash.mumong.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.domain.common.RecordingItem
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
}
