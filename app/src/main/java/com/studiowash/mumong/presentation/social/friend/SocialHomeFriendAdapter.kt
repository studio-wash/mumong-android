package com.studiowash.mumong.presentation.social.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.common.adapter.RecordingAdapter
import com.studiowash.mumong.domain.entity.common.RecordingEntity
import com.studiowash.mumong.databinding.ItemSocialFriendArticleBinding
import com.studiowash.mumong.databinding.ItemSocialFriendHeaderFriendsBinding
import com.studiowash.mumong.domain.entity.social.OnlineFriendEntity
import com.studiowash.mumong.domain.entity.social.SocialArticleEntity

class SocialHomeFriendAdapter(
    private val onClickFriend: (friendPosition: Int, item: OnlineFriendEntity) -> Unit,
    private val onClickArticle: (articlePosition: Int, item: SocialArticleEntity) -> Unit,
    private val onPlayRecording: (recording: RecordingEntity?) -> Unit,
    private val onPauseRecording: (recording: RecordingEntity?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class RecentArticleViewHolder(val binding: ItemSocialFriendArticleBinding) : RecyclerView.ViewHolder(binding.root)
    inner class HeaderFriendsViewHolder(val binding: ItemSocialFriendHeaderFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        val friendAdapter = OnlineFriendAdapter(onClickFriend)
        init { binding.friendsRecyclerView.adapter = friendAdapter }
    }

    var friends = listOf<OnlineFriendEntity>()
    var articles = listOf<SocialArticleEntity>()

    // todo : 이후 플레이어 뷰를 만들 경우 observe로 해결하는 것이 맞음.
    var recordPlayingItemIndex: Int? = null
        set(value) {
            val oldIndex = field
            field = value
            if (oldIndex != null && value != null && oldIndex != value)
                notifyItemChanged(oldIndex + ONLINE_FRIENDS_HEADER_COUNT)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_ONLINE_FRIENDS -> {
                val binding = ItemSocialFriendHeaderFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderFriendsViewHolder(binding)
            }
            else -> {
                val binding = ItemSocialFriendArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecentArticleViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderFriendsViewHolder -> {
                holder.friendAdapter.items = friends
            }
            is RecentArticleViewHolder -> {
                val articlePosition = position - ONLINE_FRIENDS_HEADER_COUNT
                val attachedRecordingAdapter = RecordingAdapter({
                    recordPlayingItemIndex = articlePosition
                    onPlayRecording.invoke(it)
                }, {
                    recordPlayingItemIndex = null
                    onPauseRecording.invoke(it)
                })

                val article = articles[articlePosition]
                holder.binding.item = article
                holder.binding.root.setOnClickListener {
                    onClickArticle.invoke(articlePosition, article)
                }
                holder.binding.recordListRecyclerView.apply {
                    adapter = attachedRecordingAdapter
                    itemAnimator = null
                }
                attachedRecordingAdapter.items = article.recordings ?: emptyList()

                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount() = articles.size + ONLINE_FRIENDS_HEADER_COUNT

    override fun getItemViewType(position: Int): Int =
        if (position == 0) VIEW_TYPE_ONLINE_FRIENDS else VIEW_TYPE_FRIEND_ARTICLE

    companion object {
        private const val VIEW_TYPE_ONLINE_FRIENDS = 0
        private const val VIEW_TYPE_FRIEND_ARTICLE = 1

        private const val ONLINE_FRIENDS_HEADER_COUNT = 1
    }
}
