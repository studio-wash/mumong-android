package com.studiowash.mumong.domain.test.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.test.entity.TestGetHelloResultEntity
import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun getHello(): Flow<RequestResult<TestGetHelloResultEntity>>
    suspend fun putData(name: String, id: Long): Flow<RequestResult<TestPutEchoResultEntity>>
}