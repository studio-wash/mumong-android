package com.studiowash.mumong.presentation.activity.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemCommunityArticleBinding

class CommunityArticleAdapter(private val onClickArticle: (articleIndex: Int, article: com.studiowash.mumong.domain.community.entity.CommunityArticleEntity) -> Unit) : RecyclerView.Adapter<CommunityArticleAdapter.RecentArticleViewHolder>() {
    class RecentArticleViewHolder(val binding: ItemCommunityArticleBinding) : RecyclerView.ViewHolder(binding.root)

    var recentArticleItems = listOf<com.studiowash.mumong.domain.community.entity.CommunityArticleEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticleViewHolder {
        val binding = ItemCommunityArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.ivUserProfile.clipToOutline = true
        binding.ivRepresentImage.clipToOutline = true
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