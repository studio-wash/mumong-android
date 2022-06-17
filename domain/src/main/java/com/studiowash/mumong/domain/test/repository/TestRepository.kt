package com.studiowash.mumong.domain.test.repository

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.common.NetworkError
import com.studiowash.mumong.domain.test.entity.TestGetHelloResultEntity
import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun getHello(): Flow<BaseResult<TestGetHelloResultEntity, NetworkError>>
    suspend fun putData(name: String, id: Long): Flow<BaseResult<TestPutEchoResultEntity, NetworkError>>
}