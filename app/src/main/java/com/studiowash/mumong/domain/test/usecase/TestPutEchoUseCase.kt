package com.studiowash.mumong.domain.test.usecase

import com.studiowash.mumong.data.test.dto.TestPutEchoResult
import com.studiowash.mumong.data.response.RequestResult
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestPutEchoUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(name: String, id: Long): Flow<RequestResult<TestPutEchoResult>> {
        return repository.putData(name, id)
    }
}