package com.studiowash.mumong.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.databinding.ItemHomeEventBinding

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    class EventViewHolder(val binding: ItemHomeEventBinding) : RecyclerView.ViewHolder(binding.root)

    var eventItems = listOf<com.studiowash.mumong.domain.EventEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemHomeEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.binding.item = eventItems[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = eventItems.size
}