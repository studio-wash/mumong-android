package com.studiowash.mumong.data.test.remote.api

import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResult
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoRequest
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoResult
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @GET("api/test")
    suspend fun testGetHello(): Response<TestGetHelloResult>

    @POST("api/test")
    suspend fun testPutEcho(@Body requst: TestPutEchoRequest): Response<TestPutEchoResult>
}