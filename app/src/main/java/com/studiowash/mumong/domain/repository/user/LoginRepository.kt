package com.studiowash.mumong.domain.repository.user

import com.studiowash.mumong.data.dto.user.LoginResultDTO
import com.studiowash.mumong.data.response.RequestResult

interface LoginRepository {
    // todo
    suspend fun requestLogin(someParam: Any) : RequestResult<LoginResultDTO>
}