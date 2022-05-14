package com.studiowash.mumong.presentation.screen.community.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunitySearchViewModel : ViewModel() {
    val seqrchQueryLiveData get() = _searchQueryLiveData
    private val _searchQueryLiveData = MutableLiveData<String>()

    fun setQuery(query: String) {
        _searchQueryLiveData.value = query
    }
}