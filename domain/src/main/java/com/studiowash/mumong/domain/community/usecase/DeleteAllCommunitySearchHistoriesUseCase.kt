package com.studiowash.mumong.domain.community.usecase

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import com.studiowash.mumong.domain.test.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAllCommunitySearchHistoriesUseCase @Inject constructor(private val repository: CommunitySearchHistoryRepository) {
    suspend operator fun invoke(): Flow<RequestResult<Boolean>> {
        return repository.deleteAllHistories()
    }
}
