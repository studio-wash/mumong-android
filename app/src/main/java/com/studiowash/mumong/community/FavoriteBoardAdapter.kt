package com.studiowash.mumong.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemCommunityBoardBinding

class FavoriteBoardAdapter : RecyclerView.Adapter<FavoriteBoardAdapter.FavoriteBoardViewHolder>() {
    class FavoriteBoardViewHolder(val binding: ItemCommunityBoardBinding) : RecyclerView.ViewHolder(binding.root)

    var favoriteBoardItems = listOf<FavoriteBoardItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBoardViewHolder {
        val binding = ItemCommunityBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteBoardViewHolder, position: Int) {
        holder.binding.item = favoriteBoardItems[position]
        holder.binding.isFirstItem = position == 0
        holder.binding.isSelected = position == 0 // todo
    }

    override fun getItemCount() = favoriteBoardItems.size
}