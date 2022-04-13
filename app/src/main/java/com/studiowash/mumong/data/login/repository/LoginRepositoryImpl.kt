package com.studiowash.mumong.data.login.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.data.login.dto.LoginResultDTO
import com.studiowash.mumong.domain.login.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun requestLogin(someParam: Any): RequestResult<LoginResultDTO> {
        TODO("Not yet implemented")
    }
}