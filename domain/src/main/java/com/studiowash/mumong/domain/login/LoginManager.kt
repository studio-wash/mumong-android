package com.studiowash.mumong.domain.login

import android.webkit.CookieManager
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.login.entity.UserEntity

object LoginManager {
    var currentUser: UserEntity? = null
    var currentToken: String? = null

    suspend fun updateUserInfoWithLastLoginToken(): UserEntity? {
        val existingToken = CookieManager.getInstance().getCookie("token")
        return getUserInfoWithMumongToken(existingToken)
    }

    private suspend fun getUserInfoWithMumongToken(token: String?): UserEntity? {
        token ?: return null

        val user = UserEntity(
            "sechiyo97@daum.net",
            "이세희",
            "별으잉",
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

    fun logout() {
        CookieManager.getInstance().removeAllCookies(null)
        currentUser = null
        currentToken = null
    }
}