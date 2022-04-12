package com.studiowash.mumong.data.test.repository

import com.studiowash.mumong.data.test.dto.TestGetHelloResult
import com.studiowash.mumong.data.test.dto.TestPutEchoRequest
import com.studiowash.mumong.data.test.dto.TestPutEchoResult
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @GET("api/test")
    suspend fun testGetHello(): Response<TestGetHelloResult>

    @POST("api/test")
    suspend fun testPutEcho(@Body requst: TestPutEchoRequest): Response<TestPutEchoResult>
}