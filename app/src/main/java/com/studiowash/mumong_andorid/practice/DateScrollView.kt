package com.studiowash.mumong_andorid.practice

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong_andorid.databinding.DateScrollViewBinding
import com.studiowash.mumong_andorid.databinding.ItemDateBinding

class DateScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = DateScrollViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val dateAdapter = DateAdapter()
    private var selectedIndex: Int = 0

    init {
        binding.dateRecyclerView.apply {
            adapter = dateAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private class DateAdapter: RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
        private class DateViewHolder(val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
            val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DateViewHolder(binding)
        }

        override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
            holder.binding.date = position + 1
        }

        override fun getItemCount() = 31
    }
}