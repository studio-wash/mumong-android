package com.studiowash.mumong.domain.test.repository

import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResult
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoResult
import com.studiowash.mumong.data.response.RequestResult
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun getHello(): Flow<RequestResult<TestGetHelloResult>>
    suspend fun putData(name: String, id: Long): Flow<RequestResult<TestPutEchoResult>>
}