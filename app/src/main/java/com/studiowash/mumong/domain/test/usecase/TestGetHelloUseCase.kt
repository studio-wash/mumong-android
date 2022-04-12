package com.studiowash.mumong.domain.test.usecase

import com.studiowash.mumong.data.test.dto.TestGetHelloResult
import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestGetHelloUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(): Flow<RequestResult<TestGetHelloResult>> {
        return repository.getHello()
    }
}