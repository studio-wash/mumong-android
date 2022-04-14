package com.studiowash.mumong.presentation.screen.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemCommunityBoardBinding

class FavoriteBoardAdapter(private val onClickBoard: (boardIndex: Int, board: com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity) -> Unit) : RecyclerView.Adapter<FavoriteBoardAdapter.FavoriteBoardViewHolder>() {
    class FavoriteBoardViewHolder(val binding: ItemCommunityBoardBinding) : RecyclerView.ViewHolder(binding.root)

    var favoriteBoardItems = listOf<com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity>()
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
        val board = favoriteBoardItems[position]
        with (holder.binding) {
            item = board
            isLastItem = position == itemCount - 1
            isSelected = position == selectedIndex
            root.setOnClickListener {
                onClickBoard.invoke(position, board)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = favoriteBoardItems.size
}