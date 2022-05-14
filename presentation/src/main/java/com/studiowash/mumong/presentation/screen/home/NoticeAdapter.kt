package com.studiowash.mumong.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemHomeNoticeBinding

class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    class NoticeViewHolder(val binding: ItemHomeNoticeBinding) : RecyclerView.ViewHolder(binding.root)

    var noticeItems = listOf<com.studiowash.mumong.domain.NoticeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemHomeNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.ivNotice.clipToOutline = true
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.binding.item = noticeItems[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = noticeItems.size
}