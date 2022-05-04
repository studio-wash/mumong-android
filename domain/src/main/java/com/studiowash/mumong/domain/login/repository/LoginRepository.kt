package com.studiowash.mumong.domain.login.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    // todo
    suspend fun requestLogin(someParam: Any) : Flow<RequestResult<LoginResultEntity>>
}