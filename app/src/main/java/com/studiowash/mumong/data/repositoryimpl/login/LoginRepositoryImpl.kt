package com.studiowash.mumong.data.repositoryimpl.login

import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.data.dto.user.LoginResultDTO
import com.studiowash.mumong.domain.repository.user.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun requestLogin(someParam: Any): RequestResult<LoginResultDTO> {
        TODO("Not yet implemented")
    }
}