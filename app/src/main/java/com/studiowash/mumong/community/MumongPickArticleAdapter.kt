package com.studiowash.mumong.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemCommunityMumongPickArticleBinding

class MumongPickArticleAdapter : RecyclerView.Adapter<MumongPickArticleAdapter.MumongPickArticleViewHolder>() {
    class MumongPickArticleViewHolder(val binding: ItemCommunityMumongPickArticleBinding) : RecyclerView.ViewHolder(binding.root)

    var mumongPickArticleItems = listOf<MumongPickArticleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MumongPickArticleViewHolder {
        val binding = ItemCommunityMumongPickArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MumongPickArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MumongPickArticleViewHolder, position: Int) {
        holder.binding.item = mumongPickArticleItems[position]
    }

    override fun getItemCount() = mumongPickArticleItems.size
}