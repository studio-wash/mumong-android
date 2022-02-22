package com.studiowash.mumong.social.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.common.AttachedRecordingAdapter
import com.studiowash.mumong.databinding.ItemSocialFriendArticleBinding
import com.studiowash.mumong.social.friend.article.SocialFriendArticleItem

class SocialFriendArticleAdapter(private val onClick: (position: Int, item: SocialFriendArticleItem) -> Unit) : RecyclerView.Adapter<SocialFriendArticleAdapter.RecentArticleViewHolder>() {
    class RecentArticleViewHolder(val binding: ItemSocialFriendArticleBinding) : RecyclerView.ViewHolder(binding.root)
    var items = listOf<SocialFriendArticleItem>()
    
    // todo : 이후 플레이어 뷰를 만들 경우 observe로 해결하는 것이 맞음.
    var recordPlayingItemIndex: Int? = null
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != null && value != null && oldIndex != value)
                notifyItemChanged(oldIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentArticleViewHolder {
        val binding = ItemSocialFriendArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentArticleViewHolder, position: Int) {
        val attachedRecordingAdapter = AttachedRecordingAdapter({
            recordPlayingItemIndex = position
        }, {
            recordPlayingItemIndex = null
        })

        val article = items[position]
        holder.binding.item = article
        holder.binding.root.setOnClickListener {
            onClick.invoke(position, article)
        }
        holder.binding.recordListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = attachedRecordingAdapter
            itemAnimator = null
        }
        attachedRecordingAdapter.items = article.attachedRecordings ?: emptyList()
    }

    override fun getItemCount() = items.size
}