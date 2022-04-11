package com.studiowash.mumong.data.repositoryimpl.login

import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.data.dto.user.LoginResultDTO
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