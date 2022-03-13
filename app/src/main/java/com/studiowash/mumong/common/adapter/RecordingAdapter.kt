package com.studiowash.mumong.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.common.model.RecordingItem
import com.studiowash.mumong.databinding.ItemRecordingBinding
import com.studiowash.mumong.singleton.MusicPlayer

class RecordingAdapter(private val onClickPlay: (recording: RecordingItem) -> Unit, private val onClickPause:(recording: RecordingItem) -> Unit) : RecyclerView.Adapter<RecordingAdapter.AttachedRecordingViewHolder>() {
    class AttachedRecordingViewHolder(val binding: ItemRecordingBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<RecordingItem>()
    var playingRecordingIndex: Int? = null
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != null)
                notifyItemChanged(oldIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachedRecordingViewHolder {
        val binding = ItemRecordingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttachedRecordingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttachedRecordingViewHolder, position: Int) {
        val recordingItem = items[position]

        holder.binding.isPlaying = playingRecordingIndex == position

        holder.binding.item = recordingItem
        holder.binding.playPauseImageView.setOnClickListener {
            val wasPlaying = holder.binding.isPlaying
            if (wasPlaying) {
                onClickPause.invoke(recordingItem)
                holder.binding.isPlaying = false
                playingRecordingIndex = null

                // todo: move this to custom recording view
                MusicPlayer.isPlaying = false
            } else {
                onClickPlay.invoke(recordingItem)
                holder.binding.isPlaying = true
                playingRecordingIndex = position

                // todo: move this to custom recording view
                MusicPlayer.currentMusic = recordingItem
                MusicPlayer.isPlaying = true
            }
        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}