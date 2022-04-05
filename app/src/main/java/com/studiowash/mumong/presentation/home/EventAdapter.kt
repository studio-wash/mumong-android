package com.studiowash.mumong.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.ItemHomeEventBinding
import com.studiowash.mumong.domain.model.home.EventItem

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    class EventViewHolder(val binding: ItemHomeEventBinding) : RecyclerView.ViewHolder(binding.root)

    var eventItems = listOf<EventItem>()

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