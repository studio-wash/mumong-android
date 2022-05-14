package com.studiowash.mumong.presentation.screen.common.attach

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemAttachedImageBinding


class AttachedImageAdapter : RecyclerView.Adapter<AttachedImageAdapter.AttachedImageViewHolder>() {
    class AttachedImageViewHolder(val binding: ItemAttachedImageBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<com.studiowash.mumong.domain.common.entity.AttachedImageEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachedImageViewHolder {
        val binding = ItemAttachedImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttachedImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttachedImageViewHolder, position: Int) {
        val imageItem = items[position]
        holder.binding.item = imageItem
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}