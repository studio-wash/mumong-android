package com.studiowash.mumong.presentation.player

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.annotation.AttrRes
import com.studiowash.mumong.presentation.databinding.ViewMusicPlayer2Binding

class MusicPlayerView2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Parcelable {
    private val binding = ViewMusicPlayer2Binding.inflate(LayoutInflater.from(context), this, true)

    val playerBodyHeight: Int get() = binding.ctlPlayerBody.measuredHeight
    val handle: View get() = binding.ivPlayerHandle

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

//        binding.ivBtnPlayPause.setOnClickListener {
//            onClickPlayPause?.invoke(this.isPlaying)
//        }

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

    constructor(parcel: Parcel) : this(
        TODO("context"),
        TODO("attrs"),
        TODO("defStyleAttr")
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MusicPlayerView2> {
        override fun createFromParcel(parcel: Parcel): MusicPlayerView2 {
            return MusicPlayerView2(parcel)
        }

        override fun newArray(size: Int): Array<MusicPlayerView2?> {
            return arrayOfNulls(size)
        }
    }
}
