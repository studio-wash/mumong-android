package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ItemSelectionBottomSheetBinding

class SelectionBottomSheet private constructor(context: Context) : BottomSheetDialog(context) {
    private val itemAdapter = ItemAdapter()

    init {
        val recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = itemAdapter
            setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, context.theme))
        }
        setContentView(
            recyclerView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        )
    }

    private fun setItems(@StringRes items: List<Int>) {
        itemAdapter.textResources = items
    }
    private fun setItemClickListener(onClickPosition: (position: Int) -> Unit) {
        itemAdapter.itemClickListener = {
            onClickPosition.invoke(it)
            this.hide()
        }
    }

    private class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        private class ItemViewHolder(val binding: ItemSelectionBottomSheetBinding): RecyclerView.ViewHolder(binding.root)
        var textResources = listOf<Int>()
        var itemClickListener: ((position: Int) -> Unit) ?= null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val binding = ItemSelectionBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.binding.tvItemText.setText(textResources[position])
            holder.binding.root.setOnClickListener {
                itemClickListener?.invoke(position)
            }
        }

        override fun getItemCount(): Int = textResources.size
    }

    class Builder(context: Context) {
        private val dialog = SelectionBottomSheet(context)

        private fun setItems(@StringRes items: List<Int>) {
            dialog.setItems(items)
        }
        private fun setItemClickListener(onClickPosition: (position: Int) -> Unit) {
            dialog.setItemClickListener(onClickPosition)
        }

        fun items(@StringRes items: List<Int>) = apply { setItems(items) }
        fun itemClickListener(onClickPosition: (position: Int) -> Unit) = apply { setItemClickListener(onClickPosition) }
        fun show() = dialog.show()
    }
}