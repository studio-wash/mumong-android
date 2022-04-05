package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.annotation.AttrRes
import com.studiowash.mumong.domain.entity.common.RecordingEntity
import com.studiowash.mumong.databinding.ViewMusicPlayerBinding

class MusicPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewMusicPlayerBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                onStartTrackingTouchListener?.invoke(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                onStopTrackingTouchListener?.invoke(seekBar)
            }
        })
    }

    var onStartTrackingTouchListener: ((seekBar: SeekBar) -> Unit) ?= null
    var onStopTrackingTouchListener: ((seekBar: SeekBar) -> Unit) ?= null

    var currentRecording: RecordingEntity? = null
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
