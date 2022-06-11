package com.studiowash.mumong.presentation.screen.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginAuthType
import com.studiowash.mumong.domain.login.entity.UserEntity
import com.studiowash.mumong.domain.login.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val requestKakaoTalkLoginUseCase: RequestKakaoTalkLoginUseCase,
    private val requestKakaoManualLoginUseCase: RequestKakaoManualLoginUseCase,
    private val requestNaverManualLoginUseCase: RequestNaverManualLoginUseCase,
    private val getUserInfoByOauthUseCase: GetUserInfoUseByOauthCase
) : ViewModel() {
    val currentUser: LiveData<UserEntity> get() = _currentUser
    private val _currentUser = MutableLiveData<UserEntity>()

    fun requestKakaoLogin(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            requestKakaoTalkLogin(context)
        } else {
            requestKakaoManualLogin(context)
        }
    }

    fun requestNaverLogin(context: Context) {
        requestNaverManualLogin(context)
    }

    private fun requestKakaoTalkLogin(context: Context) {
        viewModelScope.launch {
            requestKakaoTalkLoginUseCase(context).onStart {
//                    println("Login test on start")
            }.catch { exception ->
                Log.e("TAG", exception.stackTraceToString())
            }.collect { result ->
                when (result) {
                    is BaseResult.Success -> onLoginSuccess(LoginAuthType.Kakao, result.data.token)
                    is BaseResult.Fail -> onLoginFail()
                }
            }
        }
    }

    private fun requestKakaoManualLogin(context: Context) {
        viewModelScope.launch {
            requestKakaoManualLoginUseCase(context).onStart {
//                    println("Login test on start")
            }.catch { exception ->
                Log.e("TAG", exception.stackTraceToString())
            }.collect { result ->
                when (result) {
                    is BaseResult.Success -> onLoginSuccess(LoginAuthType.Kakao, result.data.token)
                    is BaseResult.Fail -> onLoginFail()
                }
            }
        }
    }

    private fun requestNaverManualLogin(context: Context) {
        viewModelScope.launch {
            requestNaverManualLoginUseCase(context).onStart {
//                    println("Login test on start")
            }.catch { exception ->
                Log.e("TAG", exception.stackTraceToString())
            }.collect { result ->
                when (result) {
                    is BaseResult.Success -> onLoginSuccess(LoginAuthType.Naver, result.data.token)
                    is BaseResult.Fail -> onLoginFail()
                }
            }
        }
    }

    private fun onLoginSuccess(loginAuthType: LoginAuthType, token: String) {
        viewModelScope.launch {
            getUserInfoByOauthUseCase(loginAuthType, token).onStart {
//                    println("Login test on start")
            }.catch { exception ->
                Log.e("TAG", exception.stackTraceToString())
            }.collect { result ->
                when (result) {
                    is BaseResult.Success -> _currentUser.value = result.data
                    is BaseResult.Fail -> {
                        println("Login test fail, ${result.data}")
                    }
                }
            }
        }
    }

    private fun onLoginFail() {
        println("LOGIN FAIL")
    }
}