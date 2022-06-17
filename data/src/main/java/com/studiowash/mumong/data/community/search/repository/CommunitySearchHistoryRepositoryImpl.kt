package com.studiowash.mumong.data.community.search.repository

import com.studiowash.mumong.data.community.search.local.dao.CommunitySearchHistoryDao
import com.studiowash.mumong.data.community.search.local.entity.toEntity
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.zip.DataFormatException
import javax.inject.Inject

class CommunitySearchHistoryRepositoryImpl @Inject constructor(private val historyDao: CommunitySearchHistoryDao): CommunitySearchHistoryRepository {
    override suspend fun getSearchHistories(): Flow<BaseResult<List<CommunitySearchHistory>, Throwable>> {
        return flow {
            val histories = historyDao.getSearchHistories()
            emit(BaseResult.Success(histories.map { it.toDomainModel() }))
        }
    }

    override suspend fun addHistory(history: CommunitySearchHistory): Flow<BaseResult<Boolean, Throwable>> {
        return flow {
            if (history.keyword.isBlank()) emit(BaseResult.Fail(DataFormatException("blank keyword")))
            historyDao.addRecentSearchHistory(history.toEntity())
            historyDao.deleteHistoryUntilLimitCount()
            emit(BaseResult.Success(true))
        }
    }

    override suspend fun deleteAllHistories(): Flow<BaseResult<Boolean, Throwable>> {
        return flow {
            historyDao.deleteAllHistories()
            emit(BaseResult.Success(true))
        }
    }
}