package com.studiowash.mumong.data.login.repository

import android.accounts.NetworkErrorException
import android.content.Context
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginAuthType
import com.studiowash.mumong.domain.login.LoginManager
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import com.studiowash.mumong.domain.login.entity.UserEntity
import com.studiowash.mumong.domain.login.repository.LoginRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl : LoginRepository {
    override suspend fun requestKakaoTalkLogin(context: Context): Flow<BaseResult<LoginResultEntity, Throwable>> {
        return callbackFlow {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    trySend(BaseResult.Fail(error))
                } else if (token != null) {
                    trySend(BaseResult.Success(LoginResultEntity(token.accessToken)))
                }
            }
            awaitClose()
        }
    }
    override suspend fun requestKakaoManualLogin(context: Context): Flow<BaseResult<LoginResultEntity, Throwable>> {
        return callbackFlow {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    trySend(BaseResult.Fail(error))
                } else if (token != null) {
                    trySend(BaseResult.Success(LoginResultEntity(token.accessToken)))
                }
            }
            awaitClose()
        }
    }
    override suspend fun requestNaverManualLogin(context: Context): Flow<BaseResult<LoginResultEntity, Throwable>> {
        return callbackFlow {
            val oauthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    val accessToken = NaverIdLoginSDK.getAccessToken() ?: ""
                    trySend(BaseResult.Success(LoginResultEntity(accessToken)))
                }
                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
//                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    trySend(BaseResult.Fail(NetworkErrorException(errorCode)))
                }
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            }
            NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
            awaitClose()
        }
    }

    override suspend fun updateOauthLoginInfo(
        loginAuthType: LoginAuthType,
        token: String
    ): Flow<BaseResult<UserEntity, Throwable>> {
        return flow {
            // ????????? loginAuthType, token ????????? ????????? ???????????????...

            val mumongToken = "TEST_TOKEN" // TODO
            val user = UserEntity(
                "sechiyo97@daum.net",
                "?????????",
                "?????????",
                Constants.sample_image_url,
                "?????? ????????????"
            )
            LoginManager.updateCurrentLoginInfo(user, mumongToken)
            emit(BaseResult.Success(user))
        }
    }

}