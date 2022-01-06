package com.studiowash.mumong.practice.diary.view.calendar

import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.studiowash.mumong.R
import com.studiowash.mumong.practice.diary.view.calendar.view.CustomizableCalendarView

class PracticeCalendarDayInWeekAdapter : CustomizableCalendarView.DayInWeekAdapter<PracticeCalendarDayInWeekAdapter.PracticeCalendarDayInWeekViewHolder>() {
    class PracticeCalendarDayInWeekViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticeCalendarDayInWeekViewHolder {
        val view = TextView(parent.context).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER
        }
        return PracticeCalendarDayInWeekViewHolder(view)
    }

    override fun onBindViewHolder(holder: PracticeCalendarDayInWeekViewHolder, position: Int) {
        DayInWeek.values().getOrNull(position)?.textRes?.let {
            holder.view.setText(it)
        }
    }

    override fun getItemCount() = CustomizableCalendarView.DAYS_IN_WEEK

    enum class DayInWeek(@StringRes val textRes: Int){
        Sun(R.string._customizable_calendar_sunday),
        Mon(R.string._customizable_calendar_monday),
        Tue(R.string._customizable_calendar_tuesday),
        Wed(R.string._customizable_calendar_wednesday),
        Thu(R.string._customizable_calendar_thursday),
        Fri(R.string._customizable_calendar_friday),
        Sat(R.string._customizable_calendar_satureday)
    }
}