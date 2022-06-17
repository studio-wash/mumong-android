package com.studiowash.mumong.domain.login.repository

import android.content.Context
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.login.LoginAuthType
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import com.studiowash.mumong.domain.login.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun requestKakaoTalkLogin(context: Context) : Flow<BaseResult<LoginResultEntity, Throwable>>
    suspend fun requestKakaoManualLogin(context: Context) : Flow<BaseResult<LoginResultEntity, Throwable>>
    suspend fun requestNaverManualLogin(context: Context) : Flow<BaseResult<LoginResultEntity, Throwable>>
    suspend fun updateOauthLoginInfo(loginAuthType: LoginAuthType, token: String) : Flow<BaseResult<UserEntity, Throwable>>
}