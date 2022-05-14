package com.studiowash.mumong.presentation.screen.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.EventEntity
import com.studiowash.mumong.domain.NoticeEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.home.EventAdapter
import com.studiowash.mumong.presentation.screen.home.HomeViewModel
import com.studiowash.mumong.presentation.screen.home.NoticeAdapter
import com.studiowash.mumong.presentation.screen.practice.PracticeDiaryActivity
import com.studiowash.mumong.presentation.screen.practice.addpractice.AddPracticeActivity
import com.studiowash.mumong.presentation.extension.showToast
import com.studiowash.mumong.presentation.databinding.FragmentMainHomeBinding
import com.studiowash.mumong.presentation.widget.HorizontalSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainHomeFragment : MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentMainHomeBinding? = null

    private val noticeAdapter = NoticeAdapter()
    private val eventAdapter = EventAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainHomeBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.llNotice.clipToOutline = true
        binding.llEvent.clipToOutline = true
        binding.ivBgBtnGoPractice.clipToOutline = true

        val spaceWidth = resources.getDimensionPixelSize(R.dimen.rv_horizontal_spacing_width)
        binding.rvNoticeList.apply {
            adapter = noticeAdapter
            addItemDecoration(HorizontalSpacingItemDecoration(spaceWidth))
            val noticeSnapHelper = PagerSnapHelper()
            noticeSnapHelper.attachToRecyclerView(binding.rvNoticeList)
        }
        binding.rvEventList.apply {
            adapter = eventAdapter
            addItemDecoration(HorizontalSpacingItemDecoration(spaceWidth))
            val eventSnapHelper = PagerSnapHelper()
            eventSnapHelper.attachToRecyclerView(binding.rvEventList)
        }

        // for test
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
        binding.flBtnWeeklyPractice.setOnClickListener {
            startActivity(Intent(context, PracticeDiaryActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}