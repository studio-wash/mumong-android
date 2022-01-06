package com.studiowash.mumong.practice.diary.view.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
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
    var calendarDateAdapter: CalendarDateAdapter<*> = SimpleCalendarDateAdapter()
        set(value){
            field = value
            binding.dateRecyclerView.adapter = value
        }
    var dayInWeekAdapter: DayInWeekAdapter<*> = SimpleDayInWeekAdapter()
        set(value){
            field = value
            binding.dayOfWeekRecyclerView.adapter = value
        }

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

    abstract class DayInWeekAdapter<T: RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {
        override fun getItemCount() = DAYS_IN_WEEK
    }

    abstract class CalendarDateAdapter<T: RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

        fun setYearAndMonth(year: Int, month: Int) {
            this.year = year
            this.month = month
            calendar.set(year, month, 1)
            startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            daysInThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            calendar.add(Calendar.MONTH, -1) // parse previous month
            daysInLastMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

        fun getCalendarYear() = year
        fun getCalendarMonth() = month

        private var year: Int = 0
        private var month: Int = 0
        private var calendar: Calendar = Calendar.getInstance()

        private var startDayOfWeek = Calendar.MONDAY

        private var daysInThisMonth = 31
        private var daysInLastMonth = 31

        var today = 1
        var selectedDay = 1

        override fun onBindViewHolder(holder: T, position: Int) {
            calendar.set(year, month, 1)
            val dayConcerningThisMonth = (position + 1) - (startDayOfWeek - 1)
            val monthDiff = when {
                dayConcerningThisMonth <= 0 -> -1
                dayConcerningThisMonth > daysInThisMonth -> 1
                else -> 0
            }
            calendar.add(Calendar.MONTH, monthDiff)

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)

            val dayForDisplay = when {
                dayConcerningThisMonth <= 0 -> dayConcerningThisMonth + daysInLastMonth
                dayConcerningThisMonth > daysInThisMonth -> dayConcerningThisMonth%daysInThisMonth
                else -> dayConcerningThisMonth
            }

            val isToday = dayConcerningThisMonth == today
            val isSelected = dayConcerningThisMonth == selectedDay
            val day = dayForDisplay
            val isThisMonth = year == this.year && month == this.month

            this.onBindViewHolder(holder, position, year, month, day, isToday, isSelected, isThisMonth)
        }
        abstract fun onBindViewHolder(holder: T, position: Int, year: Int, month: Int, day: Int, isToday: Boolean, isSelected: Boolean, isThisMonth: Boolean)

        override fun getItemCount() = DAYS_IN_CALENDAR_MONTH

        companion object {
            private const val WEEKS_IN_CALENDAR_MONTH = 6
            private const val DAYS_IN_CALENDAR_MONTH = DAYS_IN_WEEK * WEEKS_IN_CALENDAR_MONTH
        }
    }

    companion object {
        const val DAYS_IN_WEEK = 7
    }
}