package com.studiowash.mumong.presentation.social.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemSocialFriendOnlineFriendBinding
import com.studiowash.mumong.domain.model.social.OnlineFriendItem

class OnlineFriendAdapter(private val onClick: (position: Int, item: OnlineFriendItem) -> Unit) : RecyclerView.Adapter<OnlineFriendAdapter.OnlineFriendViewHolder>() {
    class OnlineFriendViewHolder(val binding: ItemSocialFriendOnlineFriendBinding) : RecyclerView.ViewHolder(binding.root)

    var items = listOf<OnlineFriendItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineFriendViewHolder {
        val binding = ItemSocialFriendOnlineFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnlineFriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnlineFriendViewHolder, position: Int) {
        val friend = items[position]
        with (holder.binding) {
            item = friend
            isFirstItem = position == 0
            root.setOnClickListener {
                onClick.invoke(position, friend)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}