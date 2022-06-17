package com.studiowash.mumong.presentation.screen.practice.addpractice

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.databinding.FragmentPracticeAddPracticeBinding
import com.studiowash.mumong.presentation.screen.practice.addpractice.record.RecordingStatus

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
        binding.recordButton.setOnClickListener {
            when (viewModel.recordingStatusLiveData.value) {
                RecordingStatus.IDLE -> {
                    checkPermission()
                    viewModel.startRecording(requireContext())
                }
                RecordingStatus.RECORDING -> viewModel.stopRecording()
                RecordingStatus.DONE_RECORDING -> viewModel.startPlayingRecentRecording(
                    requireContext()
                )
                RecordingStatus.PLAYING -> viewModel.stopPlayingRecentRecording()
                else -> {}
            }
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
        viewModel.recordingStatusLiveData.observe(viewLifecycleOwner) {
            binding.recordingStatus = it
        }
    }

    private fun checkPermission() {
        val activity = requireActivity()
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1234)
        }
    }
}