package com.studiowash.mumong.presentation.screen.community.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
import com.studiowash.mumong.domain.community.entity.CommunityBoardEntity
import com.studiowash.mumong.domain.community.entity.CommunityTopicEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityCommunityBinding
import com.studiowash.mumong.presentation.databinding.FragmentCommunityBoardBinding
import com.studiowash.mumong.presentation.databinding.FragmentCommunitySearchBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.community.CommunityActivity
import com.studiowash.mumong.presentation.screen.community.article.CommunityArticleViewModel
import com.studiowash.mumong.presentation.screen.community.search.model.SearchHistoryItem
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator
import com.studiowash.mumong.presentation.widget.HorizontalSpacingItemDecoration
import com.studiowash.mumong.presentation.widget.VerticalSpacingItemDecoration

class CommunitySearchFragment: MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentCommunitySearchBinding? = null

    private val viewModel: CommunitySearchViewModel by viewModels()

    val searchHistoryAdapter = CommunitySearchHistoryAdapter().apply {
        items = listOf(SearchHistoryItem("피아노"), SearchHistoryItem("밴드"), SearchHistoryItem("초보"), SearchHistoryItem("초보"), SearchHistoryItem("초보"))
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
    }

    private fun onClickBoard(boardIndex: Int, board: CommunityBoardEntity) {
    }
}