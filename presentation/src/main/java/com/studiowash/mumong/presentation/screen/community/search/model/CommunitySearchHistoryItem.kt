package com.studiowash.mumong.presentation.screen.community.search.model

import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory

data class CommunitySearchHistoryItem(val keyword: String)

fun CommunitySearchHistory.toItem() = CommunitySearchHistoryItem(keyword)