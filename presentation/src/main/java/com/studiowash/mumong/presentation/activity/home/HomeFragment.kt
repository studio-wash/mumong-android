package com.studiowash.mumong.presentation.activity.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.EventEntity
import com.studiowash.mumong.domain.NoticeEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongFragment
import com.studiowash.mumong.presentation.activity.practice.addpractice.AddPracticeActivity
import com.studiowash.mumong.presentation.common.extension.showToast
import com.studiowash.mumong.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    private val noticeAdapter = NoticeAdapter()
    private val eventAdapter = EventAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.llNotice.clipToOutline = true
        binding.llEvent.clipToOutline = true

        binding.ivBgBtnGoPractice.clipToOutline = true

        binding.rvNoticeList.adapter = noticeAdapter
        binding.rvEventList.adapter = eventAdapter

        noticeAdapter.noticeItems = listOf(
            NoticeEntity(Constants.sample_image_url),
            NoticeEntity(Constants.sample_image_url),
            NoticeEntity(Constants.sample_image_url),
            NoticeEntity(Constants.sample_image_url)
        )
        eventAdapter.eventItems = listOf(
            EventEntity(Constants.sample_image_url),
            EventEntity(Constants.sample_image_url),
            EventEntity(Constants.sample_image_url),
            EventEntity(Constants.sample_image_url)
        )
    }

    private fun initObserve() {
        viewModel.testPutEchoLoadingState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleLoadingState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.showToastEvent.observe(viewLifecycleOwner) { message ->
            activity?.showToast(message)
        }
    }

    private fun handleLoadingState(stateTestPutEcho: HomeViewModel.TestPutEchoLoadingState){
        when(stateTestPutEcho){
            is HomeViewModel.TestPutEchoLoadingState.Init -> Unit
            is HomeViewModel.TestPutEchoLoadingState.Loading -> binding.showLoadingBar = true
            is HomeViewModel.TestPutEchoLoadingState.Success -> {
                binding.showLoadingBar = false
            }
            is HomeViewModel.TestPutEchoLoadingState.Fail -> {
                binding.showLoadingBar = false
            }
        }
    }

    private fun initOnClick() {
        binding.flBtnGoPractice.setOnClickListener {
            startActivity(Intent(context, AddPracticeActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}