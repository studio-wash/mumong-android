package com.studiowash.mumong.data.test.repository

import com.studiowash.mumong.data.test.remote.api.TestApi
import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResultDTO
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoRequestDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testApi: TestApi
) : com.studiowash.mumong.domain.test.repository.TestRepository {
    override suspend fun getHello(): Flow<com.studiowash.mumong.domain.common.RequestResult<TestGetHelloResultDTO>> {
        return flow {
            val response = testApi.testGetHello()
            if (response.isSuccessful) {
                emit(com.studiowash.mumong.domain.common.RequestResult.Success(response.body()!!))
            } else {
                emit(com.studiowash.mumong.domain.common.RequestResult.Fail(response.code(), response.message()))
            }
        }
    }

    override suspend fun putData(name: String, id: Long): Flow<com.studiowash.mumong.domain.common.RequestResult<com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity>> {
        return flow {
            val response = testApi.testPutEcho(TestPutEchoRequestDTO("TEST", 3L))
            val body = response.body()!!
            if (response.isSuccessful) {
                emit(com.studiowash.mumong.domain.common.RequestResult.Success(body.toTestPutEchoResultEntity()))
            } else {
                emit(com.studiowash.mumong.domain.common.RequestResult.Fail(response.code(), response.message()))
            }
        }
    }
}