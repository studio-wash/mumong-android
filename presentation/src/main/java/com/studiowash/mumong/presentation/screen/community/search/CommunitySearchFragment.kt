package com.studiowash.mumong.presentation.screen.community.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.studiowash.mumong.domain.community.entity.CommunityBoard
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentCommunitySearchBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.community.search.model.CommunitySearchHistoryItem
import com.studiowash.mumong.presentation.widget.VerticalSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunitySearchFragment: MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentCommunitySearchBinding? = null

    private val viewModel: CommunitySearchViewModel by viewModels()

    val searchHistoryAdapter = CommunitySearchHistoryAdapter().apply {
        items = listOf(CommunitySearchHistoryItem("피아노"), CommunitySearchHistoryItem("밴드"), CommunitySearchHistoryItem("초보"), CommunitySearchHistoryItem("초보"), CommunitySearchHistoryItem("초보"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunitySearchBinding.inflate(inflater, container, false)
        //binding.viewModel = communityViewModel

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }


    private fun initView() {
        val spacing = resources.getDimensionPixelSize(R.dimen.search_history_vertical_spacing)
        binding.rvSearchHistory.apply {
            adapter = searchHistoryAdapter
            addItemDecoration(VerticalSpacingItemDecoration(spacing))
        }
    }

    private fun initOnClick() {
    }

    private fun initObserve() {
        viewModel.historyLoadingState.observe(viewLifecycleOwner) {
            when(it) {
                CommunitySearchViewModel.SearchHistoryLoadingState.Init -> {}
                CommunitySearchViewModel.SearchHistoryLoadingState.Loading -> binding.isLoadingSearchHistory = true
                is CommunitySearchViewModel.SearchHistoryLoadingState.Fail -> {
                    binding.isLoadingSearchHistory = false
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is CommunitySearchViewModel.SearchHistoryLoadingState.Success -> {
                    binding.isLoadingSearchHistory = false
                    searchHistoryAdapter.items = it.result
                    searchHistoryAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun onClickBoard(boardIndex: Int, board: CommunityBoard) {
    }
}