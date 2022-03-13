package com.studiowash.mumong.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemCommunityTagBinding

class TagAdapter(private val onClickTag: (tagIndex: Int, tag: TagItem) -> Unit) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {
    class TagViewHolder(val binding: ItemCommunityTagBinding) : RecyclerView.ViewHolder(binding.root)

    var tagItems = listOf<TagItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemCommunityTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = tagItems[position]
        holder.binding.apply {
            item = tag
            root.setOnClickListener {
                onClickTag.invoke(position, tag)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = tagItems.size
}