package com.studiowash.mumong.presentation.screen.practice.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.practice.PracticeClickEvent
import com.studiowash.mumong.presentation.screen.practice.addpractice.AddPracticeActivity
import com.studiowash.mumong.presentation.databinding.FragmentPracticeHomeBinding

class PracticeHomeFragment : Fragment(){
    private lateinit var binding: FragmentPracticeHomeBinding
    private val practiceHomeViewModel: PracticeHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeHomeBinding.inflate(inflater, container, false)
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
                    activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
                }
            }
        }
    }
}
