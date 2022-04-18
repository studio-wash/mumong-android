package com.studiowash.mumong.presentation.activity.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.common.extension.showToast
import com.studiowash.mumong.presentation.activity.profile.ProfileActivity
import com.studiowash.mumong.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    private val noticeAdapter = NoticeAdapter()
    private val eventAdapter = EventAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initView()
        initOnClick()
        initObserve()
    }

    private fun initView() {
        binding.noticeRecyclerView.adapter = noticeAdapter
        binding.eventRecyclerView.adapter = eventAdapter

        noticeAdapter.noticeItems = listOf(
            com.studiowash.mumong.domain.NoticeEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.NoticeEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.NoticeEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.NoticeEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
        )
        eventAdapter.eventItems = listOf(
            com.studiowash.mumong.domain.EventEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.EventEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.EventEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            com.studiowash.mumong.domain.EventEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
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
//        binding.profileIconImageView.setOnClickListener {
//            val intent = Intent(context, ProfileActivity::class.java)
//            startActivity(intent)
//            activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}