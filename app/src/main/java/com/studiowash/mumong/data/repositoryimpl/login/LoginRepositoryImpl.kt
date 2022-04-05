package com.studiowash.mumong.data.repositoryimpl.login

import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.data.model.user.LoginResultData
import com.studiowash.mumong.domain.repository.user.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun requestLogin(someParam: Any): RequestResult<LoginResultData> {
        TODO("Not yet implemented")
    }
}