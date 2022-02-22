package com.studiowash.mumong.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemRecordingBinding

class AttachedRecordingAdapter : RecyclerView.Adapter<AttachedRecordingAdapter.AttachedRecordingViewHolder>() {
    class AttachedRecordingViewHolder(val binding: ItemRecordingBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<AttachedRecordingItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachedRecordingViewHolder {
        val binding = ItemRecordingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttachedRecordingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttachedRecordingViewHolder, position: Int) {
        val recordingItem = items[position]
        holder.binding.item = recordingItem
    }

    override fun getItemCount() = items.size
}