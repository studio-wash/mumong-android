package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentPracticeAddPracticeBinding

class AddPracticeFragment : Fragment(R.layout.fragment_practice_add_practice) {
    private val binding get() = _binding!!
    private var _binding : FragmentPracticeAddPracticeBinding? = null

    private val viewModel: AddPracticeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPracticeAddPracticeBinding.bind(view)

        initOnClick()
        initToolbar()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        viewModel.startCountingTime()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopCountingTime()
    }

    private fun initOnClick() {
        binding.ivMetronomeBtn.setOnClickListener {
            viewModel.updateMetronomeTunerStatus(
                if (it.isSelected) AddPracticeViewModel.MetronomeTunerStatus.None
                else AddPracticeViewModel.MetronomeTunerStatus.Metronome
            )
        }
        binding.ivTunerBtn.setOnClickListener {
            viewModel.updateMetronomeTunerStatus(
                if (it.isSelected) AddPracticeViewModel.MetronomeTunerStatus.None
                else AddPracticeViewModel.MetronomeTunerStatus.Tuner
            )
        }
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initObserve() {
        viewModel.metronomeTunerStatus.observe(viewLifecycleOwner) {
            binding.metronomeTunerStatus = it
        }
        viewModel.todayPracticeTimeSec.observe(viewLifecycleOwner) {
            binding.practiceTimeSec = it
        }
    }
}