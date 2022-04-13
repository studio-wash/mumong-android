package com.studiowash.mumong.domain.test.usecase

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.test.entity.TestGetHelloResultEntity
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestGetHelloUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(): Flow<RequestResult<TestGetHelloResultEntity>> {
        return repository.getHello()
    }
}