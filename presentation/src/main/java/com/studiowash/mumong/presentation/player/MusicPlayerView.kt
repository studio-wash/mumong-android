package com.studiowash.mumong.presentation.player

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.annotation.AttrRes
import com.studiowash.mumong.presentation.databinding.ViewMusicPlayerBinding

class MusicPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewMusicPlayerBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        binding.sbSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                onStartTrackingTouchListener?.invoke(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                onStopTrackingTouchListener?.invoke(seekBar)
            }
        })

        binding.ivBtnPlayPause.setOnClickListener {
            onClickPlayPause?.invoke(this.isPlaying)
        }

        binding.ivBtnClose.setOnClickListener {
            onClickClose?.invoke()
        }
    }

    var onStartTrackingTouchListener: ((seekBar: SeekBar) -> Unit) ?= null
    var onStopTrackingTouchListener: ((seekBar: SeekBar) -> Unit) ?= null
    var onClickPlayPause: ((isPlaying: Boolean) -> Unit) ?= null
    var onClickClose: (() -> Unit) ?= null

    var isPlaying: Boolean = false
        set(value) {
            field = value
            binding.isPlaying = value
        }

    var currentRecording: com.studiowash.mumong.domain.common.entity.RecordingEntity? = null
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
