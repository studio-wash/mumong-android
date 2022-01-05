package com.studiowash.mumong.practice.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentPracticeCalendarBinding

class PracticeCalendarFragment : Fragment(){
    private lateinit var binding: FragmentPracticeCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }
}