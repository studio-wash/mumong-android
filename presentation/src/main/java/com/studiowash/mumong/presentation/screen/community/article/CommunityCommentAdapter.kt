package com.studiowash.mumong.presentation.screen.community.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.common.entity.CommentEntity
import com.studiowash.mumong.presentation.databinding.ItemCommunityArticleCommentBinding
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class CommunityCommentAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<CommunityCommentAdapter.CommunityCommentViewHolder>() {
    class CommunityCommentViewHolder(val binding: ItemCommunityArticleCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        val replyAdapter = CommunityCommentReplyAdapter({}, {})
        init {
            binding.rvReplies.adapter = replyAdapter
        }
    }

    var items = listOf<CommentEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityCommentViewHolder {
        val binding = ItemCommunityArticleCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvReplies.addItemDecoration(HorizontalDividerItemDecorator(binding.root.context))
        return CommunityCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityCommentViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
        holder.replyAdapter.items = item.replies
        holder.replyAdapter.notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}