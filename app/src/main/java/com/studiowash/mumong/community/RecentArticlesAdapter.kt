package com.studiowash.mumong.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemCommunityRecentArticleBinding

class RecentArticlesAdapter : RecyclerView.Adapter<RecentArticlesAdapter.RecentArticleViewHolder>() {
    class RecentArticleViewHolder(val binding: ItemCommunityRecentArticleBinding) : RecyclerView.ViewHolder(binding.root)

    var recentArticleItems = listOf<RecentArticleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticleViewHolder {
        val binding = ItemCommunityRecentArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentArticleViewHolder, position: Int) {
        holder.binding.item = recentArticleItems[position]
    }

    override fun getItemCount() = recentArticleItems.size
}