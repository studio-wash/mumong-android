package com.studiowash.mumong.domain.test.usecase

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.common.NetworkError
import com.studiowash.mumong.domain.test.entity.TestPutEchoResultEntity
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestPutEchoUseCase @Inject constructor(private val repository: TestRepository) {
    suspend operator fun invoke(name: String, id: Long): Flow<BaseResult<TestPutEchoResultEntity, NetworkError>> {
        return repository.putData(name, id)
    }
}