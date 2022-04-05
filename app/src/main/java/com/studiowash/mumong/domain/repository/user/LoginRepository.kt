package com.studiowash.mumong.domain.repository.user

import com.studiowash.mumong.data.user.LoginResult
import com.studiowash.mumong.domain.entity.response.RequestResult

interface LoginRepository {
    // todo
    fun requestLogin(someParam: Any) : RequestResult<LoginResult>
}