package com.studiowash.mumong.presentation.screen.community.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemCommunitySearchHistoryBinding
import com.studiowash.mumong.presentation.screen.community.search.model.SearchHistoryItem

class CommunitySearchHistoryAdapter: RecyclerView.Adapter<CommunitySearchHistoryAdapter.SearchHistoryViewHolder>() {
    class SearchHistoryViewHolder(val binding: ItemCommunitySearchHistoryBinding): RecyclerView.ViewHolder(binding.root)

    var items = listOf<SearchHistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemCommunitySearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.binding.query = items[position].query
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size
}