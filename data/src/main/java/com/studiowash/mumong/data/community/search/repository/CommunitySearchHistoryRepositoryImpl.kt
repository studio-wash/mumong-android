package com.studiowash.mumong.data.community.search.repository

import com.studiowash.mumong.data.community.search.local.dao.CommunitySearchHistoryDao
import com.studiowash.mumong.data.community.search.local.entity.toEntity
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommunitySearchHistoryRepositoryImpl @Inject constructor(private val historyDao: CommunitySearchHistoryDao): CommunitySearchHistoryRepository {
    override suspend fun getSearchHistories(): Flow<RequestResult<List<CommunitySearchHistory>>> {
        return flow {
            val histories = historyDao.getSearchHistories()
            emit(RequestResult.Success(histories.map { it.toDomainModel() }))
        }
    }

    override suspend fun addHistory(history: CommunitySearchHistory): Flow<RequestResult<Boolean>> {
        return flow {
            historyDao.addRecentSearchHistory(history.toEntity())
            emit(RequestResult.Success(true))
        }
    }

    override suspend fun deleteAllHistories(): Flow<RequestResult<Boolean>> {
        return flow {
            historyDao.deleteAllHistories()
            emit(RequestResult.Success(true))
        }
    }
}