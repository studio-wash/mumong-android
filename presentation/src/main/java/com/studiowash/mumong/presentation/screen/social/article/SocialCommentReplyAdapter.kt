package com.studiowash.mumong.presentation.screen.social.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.common.entity.CommentReplyEntity
import com.studiowash.mumong.presentation.databinding.ItemSocialCommentReplyBinding

class SocialCommentReplyAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<SocialCommentReplyAdapter.SocialCommentReplyViewHolder>() {
    class SocialCommentReplyViewHolder(val binding: ItemSocialCommentReplyBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<CommentReplyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialCommentReplyViewHolder {
        val binding = ItemSocialCommentReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SocialCommentReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocialCommentReplyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}