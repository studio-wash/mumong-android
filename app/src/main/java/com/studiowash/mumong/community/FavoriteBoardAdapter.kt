package com.studiowash.mumong.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemCommunityBoardBinding

class FavoriteBoardAdapter(private val onClickBoard: (boardIndex: Int) -> Unit) : RecyclerView.Adapter<FavoriteBoardAdapter.FavoriteBoardViewHolder>() {
    class FavoriteBoardViewHolder(val binding: ItemCommunityBoardBinding) : RecyclerView.ViewHolder(binding.root)

    var favoriteBoardItems = listOf<FavoriteBoardItem>()
    var selectedIndex: Int = 0
        set(value) {
            val oldIndex = field
            field = value
            notifyItemChanged(oldIndex)
            notifyItemChanged(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBoardViewHolder {
        val binding = ItemCommunityBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteBoardViewHolder, position: Int) {
        with (holder.binding) {
            item = favoriteBoardItems[position]
            isFirstItem = position == 0
            isSelected = position == selectedIndex
            root.setOnClickListener {
                onClickBoard.invoke(position)
            }
        }
    }

    override fun getItemCount() = favoriteBoardItems.size
}