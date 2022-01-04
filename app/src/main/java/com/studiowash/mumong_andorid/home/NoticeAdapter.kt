package com.studiowash.mumong_andorid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong_andorid.databinding.ItemNoticeBinding

class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    class NoticeViewHolder(val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root)

    var noticeItems = listOf<NoticeItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.binding.item = noticeItems[position]
    }

    override fun getItemCount() = noticeItems.size
}