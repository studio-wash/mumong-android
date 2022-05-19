package com.studiowash.mumong.domain.community.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import kotlinx.coroutines.flow.Flow

interface CommunitySearchHistoryRepository {
    suspend fun getSearchHistories() : Flow<RequestResult<List<CommunitySearchHistory>>>
    suspend fun addHistory(history: CommunitySearchHistory): Flow<RequestResult<Boolean>>
    suspend fun deleteAllHistories(): Flow<RequestResult<Boolean>>
}