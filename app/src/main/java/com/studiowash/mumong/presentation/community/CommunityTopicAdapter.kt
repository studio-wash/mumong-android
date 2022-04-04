package com.studiowash.mumong.presentation.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.R
import com.studiowash.mumong.databinding.ItemCommunityTopicBinding
import com.studiowash.mumong.domain.community.CommunityTopic

class CommunityTopicAdapter(private val onClickBest: () -> Unit, private val onClickTopic: (topicPosition: Int, topic: CommunityTopic) -> Unit) : RecyclerView.Adapter<CommunityTopicAdapter.CommunityTopicViewHolder>() {
    class CommunityTopicViewHolder(val binding: ItemCommunityTopicBinding) : RecyclerView.ViewHolder(binding.root)

    var topicItems = listOf<CommunityTopic>()
    var selectedIndex: Int? = null
        set(value) {
            val originalIndex = field
            field = value
            if (value != null)
                notifyItemChanged(value)
            if (originalIndex != null && field != originalIndex)
                notifyItemChanged(originalIndex)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTopicViewHolder {
        val binding = ItemCommunityTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityTopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityTopicViewHolder, position: Int) {
        when(getItemViewType(position)) {
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
                val topicPosition = position - BEST_HEADER_COUNT
                val topic = topicItems[topicPosition]
                holder.binding.apply {
                    isBestItem = false
                    topicNameRes = topic.topicNameRes
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
        if (position == 0) VIEW_TYPE_BEST else VIEW_TYPE_TOPIC

    override fun getItemCount() = topicItems.size + BEST_HEADER_COUNT

    companion object {
        private const val VIEW_TYPE_BEST = 0
        private const val VIEW_TYPE_TOPIC = 1

        private const val BEST_HEADER_COUNT = 1
    }
}