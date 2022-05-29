package com.studiowash.mumong.presentation.screen.community.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.community.usecase.AddCommunitySearchHistoriesUseCase
import com.studiowash.mumong.domain.community.usecase.DeleteAllCommunitySearchHistoriesUseCase
import com.studiowash.mumong.domain.community.usecase.GetCommunitySearchHistoriesUseCase
import com.studiowash.mumong.presentation.screen.community.search.model.CommunitySearchHistoryItem
import com.studiowash.mumong.presentation.screen.community.search.model.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunitySearchViewModel @Inject constructor(
    private val getSearchHistoriesUseCase: GetCommunitySearchHistoriesUseCase,
    private val addSearchHistoryUseCase: AddCommunitySearchHistoriesUseCase,
    private val deleteAllHistoriesUseCase: DeleteAllCommunitySearchHistoriesUseCase
) : ViewModel() {
    val searchQueryLiveData: LiveData<String> get() = _searchQueryLiveData
    private val _searchQueryLiveData = MutableLiveData<String>()

    val historyLoadingState: LiveData<SearchHistoryLoadingState> get() = _historyLoadingState
    private val _historyLoadingState = MutableLiveData<SearchHistoryLoadingState>(SearchHistoryLoadingState.Init)

    init {
        loadSearchHistories()
    }

    private fun loadSearchHistories() {
        viewModelScope.launch(Dispatchers.IO) {
            getSearchHistoriesUseCase().onStart {
                _historyLoadingState.postValue(SearchHistoryLoadingState.Loading)
            }.catch { exception ->
                _historyLoadingState.postValue(SearchHistoryLoadingState.Fail(exception.message))
            }.collect { result ->
                when (result) {
                    is BaseResult.Success -> _historyLoadingState.postValue(
                        SearchHistoryLoadingState.Success(result.data.map { it.toItem() })
                    )
                    is BaseResult.Fail -> _historyLoadingState.postValue(
                        SearchHistoryLoadingState.Fail(result.data.message)
                    )
                }
            }
        }
    }

    fun deleteAllHistories() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllHistoriesUseCase().catch { exception ->

            }.collect { result ->
                if (result is BaseResult.Success) {
                    loadSearchHistories()
                }
            }
        }
    }


    fun search(keyword: String) {
        if (keyword.isBlank()) return
        addKeywordToHistory(keyword.trim())

    }

    private fun addKeywordToHistory(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addSearchHistoryUseCase(keyword).catch { exception ->

            }.collect { result ->
                if (result is BaseResult.Success) {
                    loadSearchHistories()
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