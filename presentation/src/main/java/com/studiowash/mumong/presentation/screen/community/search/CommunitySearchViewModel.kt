package com.studiowash.mumong.presentation.screen.community.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.community.usecase.GetCommunitySearchHistoriesUseCase
import com.studiowash.mumong.presentation.SingleLiveEvent
import com.studiowash.mumong.presentation.screen.community.search.model.CommunitySearchHistoryItem
import com.studiowash.mumong.presentation.screen.community.search.model.toItem
import com.studiowash.mumong.presentation.screen.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunitySearchViewModel @Inject constructor(
    private val getSearchHistoriesUseCase: GetCommunitySearchHistoriesUseCase
) : ViewModel() {
    val searchQueryLiveData: LiveData<String> get() = _searchQueryLiveData
    private val _searchQueryLiveData = MutableLiveData<String>()

    val historyLoadingState: LiveData<SearchHistoryLoadingState> get() = _historyLoadingState
    private val _historyLoadingState = MutableLiveData<SearchHistoryLoadingState>(SearchHistoryLoadingState.Init)

    init {
        initTestDatabase()
        loadSearchHistories()
    }

    private fun initTestDatabase() {

    }

    fun loadSearchHistories() {
        viewModelScope.launch(Dispatchers.IO) {
            getSearchHistoriesUseCase().onStart {
                _historyLoadingState.postValue(SearchHistoryLoadingState.Loading)
            }.catch { exception ->
                _historyLoadingState.postValue(SearchHistoryLoadingState.Fail(exception.message))
            }.collect { result ->
                when (result) {
                    is RequestResult.Success -> _historyLoadingState.postValue(
                        SearchHistoryLoadingState.Success(result.data.map { it.toItem() })
                    )
                    is RequestResult.Fail -> _historyLoadingState.postValue(
                        SearchHistoryLoadingState.Fail(result.message)
                    )
                }
            }
        }
    }

    fun setQuery(query: String) {
        _searchQueryLiveData.value = query
    }

    sealed class SearchHistoryLoadingState {
        object Init : SearchHistoryLoadingState()
        object Loading : SearchHistoryLoadingState()
        data class Success(val result: List<CommunitySearchHistoryItem>) : SearchHistoryLoadingState()
        data class Fail(val message: String?) : SearchHistoryLoadingState()
    }
}