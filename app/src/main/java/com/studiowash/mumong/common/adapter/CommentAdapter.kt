package com.studiowash.mumong.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.common.model.CommentItem
import com.studiowash.mumong.databinding.ItemCommentBinding

class CommentAdapter(val onClickLike: (position: Int) -> Unit, private val onClickReply:() -> Unit) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        val replyAdapter = CommentReplyAdapter({}, {})
        init {
            binding.repliesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = replyAdapter
            }
        }
    }

    var items = listOf<CommentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = items[position]
        holder.binding.item = item
        holder.replyAdapter.items = item.replies
        holder.replyAdapter.notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}