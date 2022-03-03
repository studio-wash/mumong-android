package com.studiowash.mumong.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.common.model.AttachedImageItem
import com.studiowash.mumong.databinding.ItemAttachedImageBinding


class AttachedImageAdapter : RecyclerView.Adapter<AttachedImageAdapter.AttachedImageViewHolder>() {
    class AttachedImageViewHolder(val binding: ItemAttachedImageBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<AttachedImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachedImageViewHolder {
        val binding = ItemAttachedImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttachedImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttachedImageViewHolder, position: Int) {
        val imageItem = items[position]
        holder.binding.item = imageItem
    }

    override fun getItemCount() = items.size
}