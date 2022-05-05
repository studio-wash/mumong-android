package com.studiowash.mumong.presentation.activity.practice.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongFragment
import com.studiowash.mumong.presentation.activity.practice.PracticeClickEvent
import com.studiowash.mumong.presentation.activity.practice.addpractice.AddPracticeActivity
import com.studiowash.mumong.presentation.databinding.FragmentPracticeWeeklyBinding

class PracticeWeeklyFragment : MumongFragment(true){
    private lateinit var binding: FragmentPracticeWeeklyBinding
    private val practiceHomeViewModel: PracticeHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeWeeklyBinding.inflate(inflater, container, false)
        binding.viewModel = practiceHomeViewModel

        initView()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.year = 2022
        binding.month = 1
    }

    private fun initObserve() {
        practiceHomeViewModel.onClickLiveEvent.observe(viewLifecycleOwner) {
            when (it) {
                PracticeClickEvent.OnClickCalendarIcon -> {
                    findNavController().navigate(R.id.action_practiceFragmentNav_to_practiceCalendarFragmentNav)
                }
                PracticeClickEvent.OnClickAddNewPractice -> {
                    val intent = Intent(context, AddPracticeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
