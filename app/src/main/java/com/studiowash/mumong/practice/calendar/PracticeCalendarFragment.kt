package com.studiowash.mumong.practice.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentPracticeCalendarBinding
import java.util.*

class PracticeCalendarFragment : Fragment(){
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
        binding.calendarRecyclerView.apply {
            val calendar = Calendar.getInstance()
            setYearAndMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
            val today = Calendar.DAY_OF_MONTH
            setToday(today)
            setSelectedDay(today)
            refresh()
        }
    }
}