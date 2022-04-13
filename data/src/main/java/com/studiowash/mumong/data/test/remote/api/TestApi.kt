package com.studiowash.mumong.data.test.remote.api

import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResultDTO
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoRequestDTO
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoResultDTO
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @GET("api/test")
    suspend fun testGetHello(): Response<TestGetHelloResultDTO>

    @POST("api/test")
    suspend fun testPutEcho(@Body requst: TestPutEchoRequestDTO): Response<TestPutEchoResultDTO>
}