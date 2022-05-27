package com.studiowash.mumong.domain.community.usecase

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommunitySearchHistoriesUseCase @Inject constructor(private val repository: CommunitySearchHistoryRepository) {
    suspend operator fun invoke(): Flow<BaseResult<List<CommunitySearchHistory>, Throwable>> {
        return repository.getSearchHistories()
    }
}
