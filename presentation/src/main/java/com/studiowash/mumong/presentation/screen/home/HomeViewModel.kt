package com.studiowash.mumong.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.test.usecase.TestGetHelloUseCase
import com.studiowash.mumong.domain.test.usecase.TestPutEchoUseCase
import com.studiowash.mumong.presentation.SingleLiveEvent
import com.studiowash.mumong.presentation.screen.home.model.TestGetHelloResult
import com.studiowash.mumong.presentation.screen.home.model.TestPutEchoResult
import com.studiowash.mumong.presentation.screen.home.model.toTestPutEchoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHelloUseCase: TestGetHelloUseCase,
    private val putEchoUseCase: TestPutEchoUseCase
) : ViewModel() {

    val testPutEchoLoadingState: StateFlow<TestPutEchoLoadingState> get() = _testPutEchoLoadingState
    private val _testPutEchoLoadingState = MutableStateFlow<TestPutEchoLoadingState>(
        TestPutEchoLoadingState.Init
    )

    val testGetHelloLoadingState: StateFlow<TestGetHelloLoadingState> get() = _testGetHelloLoadingState
    private val _testGetHelloLoadingState = MutableStateFlow<TestGetHelloLoadingState>(
        TestGetHelloLoadingState.Init
    )

    val showToastEvent: LiveData<String> get() = _showToastEvent
    private val _showToastEvent = SingleLiveEvent<String>()

    init {
//        putEchoTest()
    }

    private fun getHelloTest() {
        viewModelScope.launch {
            getHelloUseCase().onStart {
                _testGetHelloLoadingState.value = TestGetHelloLoadingState.Loading
            }.catch { exception ->
                showToast(exception.stackTraceToString())
            }.collect { result ->
                when(result) {
                    is BaseResult.Success -> {
                        _testGetHelloLoadingState.value =
                            TestGetHelloLoadingState.Success(result.data)
                        showToast("getHelloUseCase Success, ${result.data}")
                    }
                    is BaseResult.Fail -> {
                        _testGetHelloLoadingState.value =
                            TestGetHelloLoadingState.Fail(result.data.code, result.data.message)
                        showToast("getHelloUseCase Fail, ${result.data.code}, ${result.data.message}")
                    }
                }
            }
        }
    }

    private fun putEchoTest() {
        viewModelScope.launch {
            putEchoUseCase("TestName", 1003L).onStart {
                _testPutEchoLoadingState.value = TestPutEchoLoadingState.Loading
            }.catch { exception ->
                showToast(exception.stackTraceToString())
                _testPutEchoLoadingState.value = TestPutEchoLoadingState.Fail(0,exception.message)
            }.collect { result ->
                when(result) {
                    is BaseResult.Success -> {
                        _testPutEchoLoadingState.value =
                            TestPutEchoLoadingState.Success(result.data.toTestPutEchoResult())
                        showToast("putEchoUseCase Success, ${result.data}")
                    }
                    is BaseResult.Fail -> {
                        _testPutEchoLoadingState.value =
                            TestPutEchoLoadingState.Fail(result.data.code, result.data.message)
                        showToast("putEchoUseCase Fail, ${result.data.code}, ${result.data.message}")
                    }
                }
            }
        }
    }

    private fun showToast(message: String){
        _showToastEvent.value = message
    }

    sealed class TestPutEchoLoadingState {
        object Init : TestPutEchoLoadingState()
        object Loading : TestPutEchoLoadingState()
        data class Success(val result: TestPutEchoResult) : TestPutEchoLoadingState()
        data class Fail(val responseCode: Int, val message: String?) : TestPutEchoLoadingState()
    }

    sealed class TestGetHelloLoadingState {
        object Init : TestGetHelloLoadingState()
        object Loading : TestGetHelloLoadingState()
        data class Success(val result: TestGetHelloResult) : TestGetHelloLoadingState()
        data class Fail(val responseCode: Int, val message: String?) : TestGetHelloLoadingState()
    }
}