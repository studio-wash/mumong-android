package com.studiowash.mumong.practice.diary.view.calendar

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.practice.diary.view.calendar.view.CustomizableCalendarView

class PracticeCalendarDateAdapter : CustomizableCalendarView.CalendarDateAdapter<PracticeCalendarDateAdapter.PracticeCalendarDateViewHolder>() {
    class PracticeCalendarDateViewHolder(val view: PracticeCalendarDateView) : RecyclerView.ViewHolder(view)

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
        holder.view.apply {
            this.day = day
            this.isToday = isToday
            this.isSelected = isSelected
            this.isThisMonth = isThisMonth
        }
    }
}