package com.studiowash.mumong.practice.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val calendarDateAdapter = CalendarDateAdapter()

    init {
        layoutManager = GridLayoutManager(context, DAYS_IN_WEEK)
        adapter = calendarDateAdapter
    }

    fun setYearAndMonth(year: Int, month: Int) {
        val calendar: Calendar = Calendar.getInstance().apply {
            set(year, month, 1)
        }
        calendarDateAdapter.startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        calendarDateAdapter.daysInThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.add(Calendar.MONTH, -1) // parse previous month
        calendarDateAdapter.daysInLastMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun setToday(day: Int) {
        calendarDateAdapter.today = day
    }
    fun setSelectedDay(day: Int) {
        calendarDateAdapter.selectedDay = day
    }

    fun refresh() = adapter?.notifyDataSetChanged()

    private class CalendarDateAdapter : Adapter<CalendarDateAdapter.CalendarDateViewHolder>() {
        var startDayOfWeek = Calendar.MONDAY
        var daysInThisMonth = 31
        var daysInLastMonth = 31
        var today = 1
        var selectedDay = 1

        private class CalendarDateViewHolder(val view: CalendarDateView) : ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDateViewHolder {
            val view = CalendarDateView(parent.context)
            return CalendarDateViewHolder(view)
        }

        override fun onBindViewHolder(holder: CalendarDateViewHolder, position: Int) {
            val dayInThisMonth = (position + 1) - (startDayOfWeek - 1)
            val dayForText = when {
                dayInThisMonth <= 0 -> dayInThisMonth + daysInLastMonth
                dayInThisMonth > daysInThisMonth -> dayInThisMonth%daysInThisMonth
                else -> dayInThisMonth
            }
            holder.view.apply {
                day = dayForText
                isToday = dayInThisMonth == today
                isSelected = dayInThisMonth == selectedDay
            }
        }

        override fun getItemCount() = DAYS_IN_CALENDAR_MONTH
    }

    companion object {
        private const val DAYS_IN_WEEK = 7
        private const val WEEKS_IN_CALENDAR_MONTH = 6
        private const val DAYS_IN_CALENDAR_MONTH = DAYS_IN_WEEK * WEEKS_IN_CALENDAR_MONTH
    }
}