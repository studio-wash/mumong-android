package com.studiowash.mumong.presentation.screen.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.domain.community.entity.CommunityTopic
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ItemCommunityTopicBinding

class CommunityTopicAdapter(private val onClickAll: () -> Unit, private val onClickBest: () -> Unit, private val onClickTopic: (topicPosition: Int, topic: CommunityTopic) -> Unit) : RecyclerView.Adapter<CommunityTopicAdapter.CommunityTopicViewHolder>() {
    class CommunityTopicViewHolder(val binding: ItemCommunityTopicBinding) : RecyclerView.ViewHolder(binding.root)

    var topicItems = listOf<CommunityTopic>()
    var selectedIndex: Int = 0
        set(value) {
            val originalIndex = field
            field = value
            notifyItemChanged(value)
            if (field != originalIndex)
                notifyItemChanged(originalIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTopicViewHolder {
        val binding = ItemCommunityTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityTopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityTopicViewHolder, position: Int) {
        when(getItemViewType(position)) {
            VIEW_TYPE_ALL -> {
                holder.binding.apply {
                    topicNameRes = R.string.community_topic_all
                    root.setOnClickListener {
                        selectedIndex = position
                        onClickAll.invoke()
                    }
                }
            }
            VIEW_TYPE_BEST -> {
                holder.binding.apply {
                    isBestItem = true
                    topicNameRes = R.string.community_topic_best
                    root.setOnClickListener {
                        selectedIndex = position
                        onClickBest.invoke()
                    }
                }
            }
            else -> {
                val topicPosition = position - ITEM_HEADER_COUNT
                val topic = topicItems[topicPosition]
                holder.binding.apply {
                    isBestItem = false
                    topicNameRes = when(topic) {
                        CommunityTopic.DAILY -> R.string.community_topic_daily
                        CommunityTopic.RECORDING -> R.string.community_topic_recording
                        CommunityTopic.TIP -> R.string.community_topic_tip
                        CommunityTopic.QUESTION -> R.string.community_topic_question
                        CommunityTopic.ADVERTISE -> R.string.community_topic_advertise
                    }
                    root.setOnClickListener {
                        selectedIndex = position
                        onClickTopic.invoke(topicPosition, topic)
                    }
                }
            }
        }
        holder.binding.apply {
            isSelected = position == selectedIndex
            isLastItem = position == itemCount - 1
            executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> VIEW_TYPE_ALL
            1 -> VIEW_TYPE_BEST
            else -> VIEW_TYPE_TOPIC
        }

    override fun getItemCount() = topicItems.size + ITEM_HEADER_COUNT

    companion object {
        private const val VIEW_TYPE_ALL = 0
        private const val VIEW_TYPE_BEST = 1
        private const val VIEW_TYPE_TOPIC = 2

        private const val ITEM_HEADER_COUNT = 2
    }
}