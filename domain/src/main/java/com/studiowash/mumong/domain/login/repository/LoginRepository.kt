package com.studiowash.mumong.domain.login.repository

import android.content.Context
import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun requestKakaoTalkLogin(context: Context) : Flow<RequestResult<LoginResultEntity>>
    suspend fun requestKakaoManualLogin(context: Context) : Flow<RequestResult<LoginResultEntity>>
}