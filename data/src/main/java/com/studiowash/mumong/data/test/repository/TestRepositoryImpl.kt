package com.studiowash.mumong.data.test.repository

import com.studiowash.mumong.data.test.remote.api.TestApi
import com.studiowash.mumong.data.test.remote.dto.TestGetHelloResultDTO
import com.studiowash.mumong.data.test.remote.dto.TestPutEchoRequestDTO
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.common.NetworkError
import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val testApi: TestApi
) : TestRepository {
    override suspend fun getHello(): Flow<BaseResult<TestGetHelloResultDTO, NetworkError>> {
        return flow {
            val response = testApi.testGetHello()
            if (response.isSuccessful) {
                emit(BaseResult.Success(response.body()!!))
            } else {
                emit(BaseResult.Fail(NetworkError(response.code(), response.message())))
            }
        }
    }

    override suspend fun putData(name: String, id: Long): Flow<BaseResult<TestPutEchoResultEntity, NetworkError>> {
        return flow {
            val response = testApi.testPutEcho(TestPutEchoRequestDTO("TEST", 3L))
            val body = response.body()!!
            if (response.isSuccessful) {
                emit(BaseResult.Success(body.toTestPutEchoResultEntity()))
            } else {
                emit(BaseResult.Fail(NetworkError(response.code(), response.message())))
            }
        }
    }
}