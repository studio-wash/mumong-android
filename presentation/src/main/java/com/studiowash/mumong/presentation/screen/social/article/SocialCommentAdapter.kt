package com.studiowash.mumong.presentation.screen.social.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.common.entity.CommentEntity
import com.studiowash.mumong.presentation.databinding.ItemSocialArticleCommentBinding
import com.studiowash.mumong.presentation.screen.common.comment.CommentReplyAdapter

class SocialCommentAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<SocialCommentAdapter.SocialCommentViewHolder>() {
    class SocialCommentViewHolder(val binding: ItemSocialArticleCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        val replyAdapter = CommentReplyAdapter({}, {})
        init {
            binding.repliesRecyclerView.adapter = replyAdapter
        }
    }

    var items = listOf<CommentEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialCommentViewHolder {
        val binding = ItemSocialArticleCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SocialCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocialCommentViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
        holder.replyAdapter.items = item.replies
        holder.replyAdapter.notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}