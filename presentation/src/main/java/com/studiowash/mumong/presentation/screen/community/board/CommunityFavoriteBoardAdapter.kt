package com.studiowash.mumong.presentation.screen.community.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.community.entity.CommunityBoardEntity
import com.studiowash.mumong.presentation.databinding.ItemMainCommunityFavoriteBoardBinding

class CommunityFavoriteBoardAdapter(private val onClickFavoritePin: (position: Int, board: CommunityBoardEntity) -> Unit, private val onClickBoard: (position: Int, board: CommunityBoardEntity) -> Unit) : RecyclerView.Adapter<CommunityFavoriteBoardAdapter.CommunityFavoriteBoardViewHolder>() {
    class CommunityFavoriteBoardViewHolder(val binding: ItemMainCommunityFavoriteBoardBinding) : RecyclerView.ViewHolder(binding.root)

    var boardItems = listOf<CommunityBoardEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityFavoriteBoardViewHolder {
        val binding = ItemMainCommunityFavoriteBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityFavoriteBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityFavoriteBoardViewHolder, position: Int) {
        val item = boardItems[position]
        holder.binding.apply {
            title = item.boardName
            recentArticleContent = item.recentArticleContent
            hasNewArticle = item.hasNewArticle
            isFavorite = true
            root.setOnClickListener {
                onClickBoard.invoke(position, item)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = boardItems.size
}