package com.studiowash.mumong.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemRecordingBinding

class AttachedRecordingAdapter(private val onClickPlay: () -> Unit, private val onClickPause:() -> Unit) : RecyclerView.Adapter<AttachedRecordingAdapter.AttachedRecordingViewHolder>() {
    class AttachedRecordingViewHolder(val binding: ItemRecordingBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<AttachedRecordingItem>()
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
                onClickPause.invoke()
                holder.binding.isPlaying = false
                playingRecordingIndex = null
            } else {
                onClickPlay.invoke()
                holder.binding.isPlaying = true
                playingRecordingIndex = position
            }
        }
    }

    override fun getItemCount() = items.size
}