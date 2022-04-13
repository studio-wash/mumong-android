package com.studiowash.mumong.data.login.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.data.login.dto.LoginResultDTO
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    // todo
    @POST("")
    suspend fun login(
        @Query("id") id: String,
        @Query("password") password: String
    ): RequestResult<LoginResultDTO>
}