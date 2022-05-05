package com.studiowash.mumong.data.login.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.login.entity.LoginResultEntity
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl : com.studiowash.mumong.domain.login.repository.LoginRepository {
    override suspend fun requestLogin(someParam: Any): Flow<RequestResult<LoginResultEntity>> {
        TODO("Not yet implemented")
    }
}