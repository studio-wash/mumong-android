package com.studiowash.mumong.presentation.screen.practice.diary.view.calendar

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.presentation.screen.practice.diary.view.calendar.view.CustomizableCalendarView
import kotlin.random.Random

class PracticeCalendarDateAdapter : CustomizableCalendarView.CalendarDateAdapter<PracticeCalendarDateAdapter.PracticeCalendarDateViewHolder>() {
    class PracticeCalendarDateViewHolder(val view: PracticeCalendarDateView) : RecyclerView.ViewHolder(view)

    val random = Random(System.currentTimeMillis())
    val targetPracticeSeconds = 3600
    val practiceOfDays = List(42) {
        com.studiowash.mumong.domain.PracticeOfDay(random.nextInt(0, 3 * targetPracticeSeconds))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PracticeCalendarDateViewHolder {
        val view = PracticeCalendarDateView(parent.context)
        return PracticeCalendarDateViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PracticeCalendarDateViewHolder,
        position: Int,
        year: Int,
        month: Int,
        day: Int,
        isToday: Boolean,
        isSelected: Boolean,
        isThisMonth: Boolean
    ) {
        val practiceOfDay = practiceOfDays[position]
        holder.view.apply {
            this.day = day
            this.isToday = isToday
            this.isSelected = isSelected
            this.isThisMonth = isThisMonth
            practicePercent = practiceOfDay.practiceTimeInSeconds.toFloat() / targetPracticeSeconds
        }
    }
}