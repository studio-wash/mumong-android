package com.studiowash.mumong.data.login.repository

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import com.studiowash.mumong.domain.login.repository.LoginRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LoginRepositoryImpl : LoginRepository {
    override suspend fun requestKakaoTalkLogin(context: Context): Flow<RequestResult<LoginResultEntity>> {
        return callbackFlow {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    trySend(RequestResult.Fail(message = error.cause?.message))
                } else if (token != null) {
                    trySend(RequestResult.Success(LoginResultEntity(token.accessToken)))
                }
            }
            awaitClose()
        }
    }
    override suspend fun requestKakaoManualLogin(context: Context): Flow<RequestResult<LoginResultEntity>> {
        return callbackFlow {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    trySend(RequestResult.Fail(message = error.cause?.message))
                } else if (token != null) {
                    trySend(RequestResult.Success(LoginResultEntity(token.accessToken)))
                }
            }
            awaitClose()
        }
    }
}