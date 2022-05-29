package com.studiowash.mumong.domain.community.repository

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import kotlinx.coroutines.flow.Flow

interface CommunitySearchHistoryRepository {
    suspend fun getSearchHistories() : Flow<BaseResult<List<CommunitySearchHistory>, Throwable>>
    suspend fun addHistory(history: CommunitySearchHistory): Flow<BaseResult<Boolean, Throwable>>
    suspend fun deleteAllHistories(): Flow<BaseResult<Boolean, Throwable>>
}