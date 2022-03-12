package com.studiowash.mumong.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.common.model.CommentReplyItem
import com.studiowash.mumong.databinding.ItemCommentReplyBinding

class CommentReplyAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<CommentReplyAdapter.CommentReplyViewHolder>() {
    class CommentReplyViewHolder(val binding: ItemCommentReplyBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<CommentReplyItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentReplyViewHolder {
        val binding = ItemCommentReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentReplyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
    }

    override fun getItemCount() = items.size
}