package com.studiowash.mumong.data.community.search

import com.studiowash.mumong.data.AppDatabase
import com.studiowash.mumong.data.NetworkModule
import com.studiowash.mumong.data.community.search.local.dao.CommunitySearchHistoryDao
import com.studiowash.mumong.data.community.search.repository.CommunitySearchHistoryRepositoryImpl
import com.studiowash.mumong.domain.community.repository.CommunitySearchHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class CommunitySearchModule {
    @Provides
    fun providesCommunitySearchHistoryDao(database: AppDatabase) = database.communitySearchHistoryDao()

    @Provides
    fun provideSearchHistoryRepository(dao: CommunitySearchHistoryDao) : CommunitySearchHistoryRepository {
        return CommunitySearchHistoryRepositoryImpl(dao)
    }
}