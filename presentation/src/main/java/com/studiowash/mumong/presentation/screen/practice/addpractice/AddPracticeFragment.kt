package com.studiowash.mumong.presentation.screen.practice.addpractice

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.databinding.FragmentPracticeAddPracticeBinding
import com.studiowash.mumong.presentation.screen.practice.addpractice.record.RecordingStatus

class AddPracticeFragment : MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding : FragmentPracticeAddPracticeBinding? = null

    private val viewModel: AddPracticeViewModel by viewModels()
    
    private val recordPermissionCheckLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.startRecording(requireContext())
            } else {
                showAlertDialogForAppPermissionSetting()
            }
        }
    
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
                    if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        viewModel.startRecording(requireContext())
                    } else {
                        recordPermissionCheckLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
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

    private fun showAlertDialogForAppPermissionSetting() {
        AlertDialog.Builder(context)
            .setMessage("녹음 기능을 사용하기 위해서는 마이크 권한이 필요합니다. 앱 설정으로 이동하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", activity?.packageName, null)
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
            }
            .setNegativeButton("아니오") { _, _ -> }
            .create().show()

    }
//    private fun checkPermission() {
//        val activity = requireActivity()
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//            || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//            || ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), 1234)
//        }
//    }
}