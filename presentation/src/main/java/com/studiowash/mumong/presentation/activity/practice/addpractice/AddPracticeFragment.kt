package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongFragment
import com.studiowash.mumong.presentation.databinding.FragmentPracticeAddPracticeBinding

class AddPracticeFragment : MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding : FragmentPracticeAddPracticeBinding? = null

    private val viewModel: AddPracticeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPracticeAddPracticeBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initToolbar()
        initObserve()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.startCountingTime()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopCountingTime()
    }

    private fun initView() {

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