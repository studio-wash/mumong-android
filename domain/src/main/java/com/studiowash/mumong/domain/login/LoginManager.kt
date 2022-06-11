package com.studiowash.mumong.domain.login

import android.webkit.CookieManager
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.login.entity.UserEntity

object LoginManager {
    var currentUser: UserEntity? = null
    var currentToken: String? = null

    suspend fun updateUserInfoWithLastLoginToken(): UserEntity? {
        val existingToken = CookieManager.getInstance().getCookie("token")
        existingToken ?: return null
        return getUserInfoWithMumongToken(existingToken)
    }

    private suspend fun getUserInfoWithMumongToken(token: String): UserEntity {
        val user = UserEntity(
            "sechiyo97@daum.net",
            "이세희-자동로그인",
            "별으잉-자동로그인",
            Constants.sample_image_url,
            "샘플 자기소개"
        )
        updateCurrentLoginInfo(user, token)
        return user
    }

    fun updateCurrentLoginInfo(user: UserEntity, token: String) {
        currentUser = user
        currentToken = token
        CookieManager.getInstance().setCookie("token", token)
    }
}