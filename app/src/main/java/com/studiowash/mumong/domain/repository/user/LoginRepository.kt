package com.studiowash.mumong.domain.repository.user

import com.studiowash.mumong.data.model.user.LoginResultData
import com.studiowash.mumong.data.response.RequestResult

interface LoginRepository {
    // todo
    suspend fun requestLogin(someParam: Any) : RequestResult<LoginResultData>
}