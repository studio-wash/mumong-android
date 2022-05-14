package com.studiowash.mumong.presentation.screen.community.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.common.entity.CommentReplyEntity
import com.studiowash.mumong.presentation.databinding.ItemCommunityCommentReplyBinding
import com.studiowash.mumong.presentation.databinding.ItemSocialCommentReplyBinding

class CommunityCommentReplyAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<CommunityCommentReplyAdapter.CommunityCommentReplyViewHolder>() {
    class CommunityCommentReplyViewHolder(val binding: ItemCommunityCommentReplyBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<CommentReplyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityCommentReplyViewHolder {
        val binding = ItemCommunityCommentReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityCommentReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityCommentReplyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}