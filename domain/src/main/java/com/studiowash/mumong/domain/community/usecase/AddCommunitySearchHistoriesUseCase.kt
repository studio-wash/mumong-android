package com.studiowash.mumong.domain.community.usecase

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCommunitySearchHistoriesUseCase @Inject constructor(private val repository: CommunitySearchHistoryRepository) {
    suspend operator fun invoke(keyword: String): Flow<BaseResult<Boolean, Throwable>> {
        return repository.addHistory(CommunitySearchHistory(keyword))
    }
}
