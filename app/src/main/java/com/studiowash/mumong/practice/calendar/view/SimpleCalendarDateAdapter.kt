package com.studiowash.mumong.practice.calendar.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SimpleCalendarDateAdapter : CustomizableCalendarView.CalendarDateAdapter<SimpleCalendarDateAdapter.SimpleCalendarDateViewHolder>() {

    class SimpleCalendarDateViewHolder(val view: SimpleCalendarDateView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleCalendarDateViewHolder {
        val view = SimpleCalendarDateView(parent.context)
        return SimpleCalendarDateViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SimpleCalendarDateViewHolder,
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
