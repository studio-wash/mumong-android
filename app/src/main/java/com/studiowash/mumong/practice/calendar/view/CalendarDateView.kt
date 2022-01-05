package com.studiowash.mumong.practice.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.databinding.ItemPracticeCalendarDateBinding

class CalendarDateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ItemPracticeCalendarDateBinding.inflate(LayoutInflater.from(context), this, true)

    var day: Int = 0
        set(value) {
            field = value
            binding.day = value
        }
    var isToday: Boolean = false
        set(value) {
            field = value
            binding.isToday = value
        }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        binding.isSelected = selected
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}