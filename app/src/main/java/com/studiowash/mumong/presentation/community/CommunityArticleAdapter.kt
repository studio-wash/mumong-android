package com.studiowash.mumong.presentation.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.community.CommunityArticleItem
import com.studiowash.mumong.databinding.ItemCommunityArticleBinding

class CommunityArticleAdapter(private val onClickArticle: (articleIndex: Int, article: CommunityArticleItem) -> Unit) : RecyclerView.Adapter<CommunityArticleAdapter.RecentArticleViewHolder>() {
    class RecentArticleViewHolder(val binding: ItemCommunityArticleBinding) : RecyclerView.ViewHolder(binding.root)

    var recentArticleItems = listOf<CommunityArticleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticleViewHolder {
        val binding = ItemCommunityArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentArticleViewHolder, position: Int) {
        val article = recentArticleItems[position]
        holder.binding.item = article
        holder.binding.root.setOnClickListener {
            onClickArticle.invoke(position, article)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = recentArticleItems.size
}