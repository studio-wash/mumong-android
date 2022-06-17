package com.studiowash.mumong.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studiowash.mumong.domain.login.LoginManager
import com.studiowash.mumong.presentation.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitActivityViewModel @Inject constructor() : ViewModel() {
    val redirectPage: SingleLiveEvent<RedirectPage> get() = _redirectPage
    private val _redirectPage = SingleLiveEvent<RedirectPage>()

    fun getLastLoginInfo() {
        viewModelScope.launch {
            val loginInfo = LoginManager.updateUserInfoWithLastLoginToken()
            if (loginInfo == null)
                _redirectPage.value = RedirectPage.Login
            else
                _redirectPage.value = RedirectPage.Main
        }
    }
}