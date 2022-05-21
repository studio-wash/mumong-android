package com.studiowash.mumong.presentation.screen.community.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.community.entity.CommunityBoard
import com.studiowash.mumong.presentation.databinding.ItemMainCommunityAllBoardBinding

class CommunityAllBoardAdapter(private val onClickFavoritePin: (position: Int, board: CommunityBoard) -> Unit, private val onClickBoard: (position: Int, board: CommunityBoard) -> Unit) : RecyclerView.Adapter<CommunityAllBoardAdapter.CommunityAllBoardViewHolder>() {
    class CommunityAllBoardViewHolder(val binding: ItemMainCommunityAllBoardBinding) : RecyclerView.ViewHolder(binding.root)

    var boardItems = listOf<CommunityBoard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityAllBoardViewHolder {
        val binding = ItemMainCommunityAllBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityAllBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityAllBoardViewHolder, position: Int) {
        val item = boardItems[position]
        holder.binding.apply {
            title = item.boardName
            hasNewArticle = item.hasNewArticle
            isFavorite = item.isFavorite
            root.setOnClickListener {
                onClickBoard.invoke(position, item)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = boardItems.size
}