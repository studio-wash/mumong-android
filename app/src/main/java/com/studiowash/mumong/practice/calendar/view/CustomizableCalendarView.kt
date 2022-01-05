package com.studiowash.mumong.practice.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.databinding.CustomizableCalendarViewBinding
import java.util.*

class CustomizableCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = CustomizableCalendarViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val calendarDateAdapter = CalendarDateAdapter()
    private val dayInWeekAdapter = DayInWeekAdapter()

    init {
        binding.dateRecyclerView.apply {
            layoutManager = GridLayoutManager(context, DAYS_IN_WEEK)
            adapter = calendarDateAdapter
        }
        binding.dayOfWeekRecyclerView.apply {
            layoutManager = GridLayoutManager(context, DAYS_IN_WEEK)
            adapter = dayInWeekAdapter
        }
    }

    fun setYearAndMonth(year: Int, month: Int) {
        calendarDateAdapter.setYearAndMonth(year, month)
    }

    fun setToday(day: Int) {
        calendarDateAdapter.today = day
    }
    fun setSelectedDay(day: Int) {
        calendarDateAdapter.selectedDay = day
    }

    fun refresh() = binding.dateRecyclerView.adapter?.notifyDataSetChanged()

    private class CalendarDateAdapter : RecyclerView.Adapter<CalendarDateAdapter.CalendarDateViewHolder>() {

        fun setYearAndMonth(year: Int, month: Int) {
            val calendar: Calendar = Calendar.getInstance().apply {
                set(year, month, 1)
            }
            startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            daysInThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            calendar.add(Calendar.MONTH, -1) // parse previous month
            daysInLastMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

        private var startDayOfWeek = Calendar.MONDAY

        private var daysInThisMonth = 31
        private var daysInLastMonth = 31

        var today = 1
        var selectedDay = 1

        private class CalendarDateViewHolder(val view: CustomizableCalendarDateView) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDateViewHolder {
            val view = CustomizableCalendarDateView(parent.context)
            return CalendarDateViewHolder(view)
        }

        override fun onBindViewHolder(holder: CalendarDateViewHolder, position: Int) {
            val dayInThisMonth = (position + 1) - (startDayOfWeek - 1)
            val day = when {
                dayInThisMonth <= 0 -> dayInThisMonth + daysInLastMonth
                dayInThisMonth > daysInThisMonth -> dayInThisMonth%daysInThisMonth
                else -> dayInThisMonth
            }
            holder.view.apply {
                this.day = day
                isToday = dayInThisMonth == today
                isSelected = dayInThisMonth == selectedDay
                isThisMonth = dayInThisMonth == day
            }
        }

        override fun getItemCount() = DAYS_IN_CALENDAR_MONTH
    }

    private class DayInWeekAdapter : RecyclerView.Adapter<DayInWeekAdapter.DayInWeekViewHolder>() {
        private class DayInWeekViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayInWeekViewHolder {
            val view = TextView(parent.context).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                gravity = Gravity.CENTER
            }
            return DayInWeekViewHolder(view)
        }

        override fun onBindViewHolder(holder: DayInWeekViewHolder, position: Int) {
            holder.view.text = DayInWeek.values().getOrNull(position)?.text
        }

        override fun getItemCount() = DAYS_IN_WEEK
    }

    companion object {
        private const val DAYS_IN_WEEK = 7
        private const val WEEKS_IN_CALENDAR_MONTH = 6
        private const val DAYS_IN_CALENDAR_MONTH = DAYS_IN_WEEK * WEEKS_IN_CALENDAR_MONTH

        private enum class DayInWeek(val text: String){ Sun("일"), Mon("월"), Tue("화"), Wed("수"), Thu("목"), Fri("금"), Sat("토") }
    }
}