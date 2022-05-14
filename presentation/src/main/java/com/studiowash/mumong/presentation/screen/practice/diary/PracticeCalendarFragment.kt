package com.studiowash.mumong.presentation.screen.practice.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.practice.diary.view.calendar.PracticeCalendarDateAdapter
import com.studiowash.mumong.presentation.screen.practice.diary.view.calendar.PracticeCalendarDayInWeekAdapter
import com.studiowash.mumong.presentation.databinding.FragmentPracticeCalendarBinding
import java.util.*

class PracticeCalendarFragment : MumongFragment(true){
    private lateinit var binding: FragmentPracticeCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeCalendarBinding.inflate(inflater, container, false)
        initDate()
        return binding.root
    }

    private fun initDate() {
        binding.calendarView.apply {
            calendarDateAdapter = PracticeCalendarDateAdapter()
            dayInWeekAdapter = PracticeCalendarDayInWeekAdapter()

            val calendar = Calendar.getInstance()
            val today = calendar.get(Calendar.DAY_OF_MONTH)

            setYearAndMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
            setToday(today)
            setSelectedDay(today)

            refresh()
        }
    }
}