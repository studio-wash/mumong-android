package com.studiowash.mumong.data.test.repository

import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResult
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoRequest
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoResult
import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.data.test.remote.api.TestApi
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testApi: TestApi
) : TestRepository {
    override suspend fun getHello(): Flow<RequestResult<TestGetHelloResult>> {
        return flow {
            val response = testApi.testGetHello()
            if (response.isSuccessful) {
                emit(RequestResult.Success(response.body()!!))
            } else {
                emit(RequestResult.Fail(response.code(), response.message()))
            }
        }
    }

    override suspend fun putData(name: String, id: Long): Flow<RequestResult<TestPutEchoResult>> {
        return flow {
            val response = testApi.testPutEcho(TestPutEchoRequest("TEST", 3L))
            val body = response.body()!!
            if (response.isSuccessful) {
                emit(RequestResult.Success(body))
            } else {
                emit(RequestResult.Fail(response.code(), response.message()))
            }
        }
    }
}