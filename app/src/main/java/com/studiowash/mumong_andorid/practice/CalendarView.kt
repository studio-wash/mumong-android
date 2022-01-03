package com.studiowash.mumong_andorid.practice

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong_andorid.databinding.CalendarViewBinding

class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    val binding = CalendarViewBinding.inflate(LayoutInflater.from(context), this, true)
}