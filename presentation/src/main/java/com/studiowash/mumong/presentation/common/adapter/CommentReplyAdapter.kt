package com.studiowash.mumong.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.common.entity.CommentReplyEntity
import com.studiowash.mumong.presentation.databinding.ItemCommentReplyBinding

class CommentReplyAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<CommentReplyAdapter.CommentReplyViewHolder>() {
    class CommentReplyViewHolder(val binding: ItemCommentReplyBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<CommentReplyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentReplyViewHolder {
        val binding = ItemCommentReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentReplyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size
}