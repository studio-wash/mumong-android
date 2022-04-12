package com.studiowash.mumong.domain.login.repository

import com.studiowash.mumong.data.login.dto.LoginResultDTO
import com.studiowash.mumong.data.response.RequestResult

interface LoginRepository {
    // todo
    suspend fun requestLogin(someParam: Any) : RequestResult<LoginResultDTO>
}