package com.studiowash.mumong.practice.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.DateScrollViewBinding
import com.studiowash.mumong.databinding.ItemDateBinding

class DateScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = DateScrollViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val dateAdapter = DefaultDateAdapter()

    private var selectedPosition: Int = 3
    private var todayPosition: Int = 13

    init {
        binding.dateRecyclerView.apply {
            adapter = dateAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dateAdapter.apply {
            todayPosition = this@DateScrollView.todayPosition
            selectedPosition = this@DateScrollView.selectedPosition
        }

        binding.dateRecyclerView.scrollToPosition(selectedPosition)
    }

    private class DefaultDateAdapter: RecyclerView.Adapter<DefaultDateAdapter.DateViewHolder>() {
        var todayPosition: Int = 0
        var selectedPosition: Int = 0

        private class DateViewHolder(val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
            val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DateViewHolder(binding)
        }

        override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
            holder.binding.isSelected = selectedPosition == position
            holder.binding.isToday = todayPosition == position
            holder.binding.date = position + 1
        }

        override fun getItemCount() = 31
    }
}