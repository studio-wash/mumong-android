package com.studiowash.mumong.presentation.screen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.usecase.RequestKakaoManualLoginUseCase
import com.studiowash.mumong.domain.login.usecase.RequestKakaoTalkLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val requestKakaoTalkLoginUseCase: RequestKakaoTalkLoginUseCase,
    private val requestKakaoManualLoginUseCase: RequestKakaoManualLoginUseCase
) : ViewModel() {
    fun requestKakaoLogin(context: Context) {
        viewModelScope.launch {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                requestKakaoTalkLoginUseCase(context).onStart {
                    println("Login test on start")
                }.catch { exception ->
                    println(exception.stackTraceToString())
                }.collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            println("Login test success, token=${result.data.token}")
                        }
                        is BaseResult.Fail -> {
                            println("Login test fail, ${result.data}")
                        }
                    }
                }
            } else {
                requestKakaoManualLoginUseCase(context).onStart {
                    println("Login test on start")
                }.catch { exception ->
                    println(exception.stackTraceToString())
                }.collect { result ->
                    when (result) {
                        is BaseResult.Success -> {
                            println("Login test success, token=${result.data.token}")
                        }
                        is BaseResult.Fail -> {
                            println("Login test fail, ${result.data}")
                        }
                    }
                }
            }
        }
    }
}