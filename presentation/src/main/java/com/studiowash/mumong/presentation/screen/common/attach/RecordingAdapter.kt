package com.studiowash.mumong.presentation.screen.common.attach

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecordingAdapter(private val onPlayRecording: (recording: com.studiowash.mumong.domain.common.entity.RecordingEntity) -> Unit, private val onPauseRecording:(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity) -> Unit) : RecyclerView.Adapter<RecordingAdapter.AttachedRecordingViewHolder>() {
    class AttachedRecordingViewHolder(val view: RecordingItemView) : RecyclerView.ViewHolder(view)

    var items = listOf<com.studiowash.mumong.domain.common.entity.RecordingEntity>()
    var playingRecordingIndex: Int? = null
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != null)
                notifyItemChanged(oldIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachedRecordingViewHolder =
        AttachedRecordingViewHolder(RecordingItemView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        })

    override fun onBindViewHolder(holder: AttachedRecordingViewHolder, position: Int) {
        val recordingItem = items[position]

        holder.view.isPlaying = playingRecordingIndex == position

        holder.view.item = recordingItem
        holder.view.onPlayingStatusChanged = { isPlaying ->
            playingRecordingIndex = if (isPlaying) {
                onPlayRecording.invoke(recordingItem)
                position
            } else {
                onPauseRecording.invoke(recordingItem)
                null
            }
        }
    }

    override fun getItemCount() = items.size
}