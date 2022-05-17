package com.studiowash.mumong.data.community.search.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studiowash.mumong.data.community.search.local.entity.CommunitySearchHistoryEntity

@Dao
interface CommunitySearchHistoryDao {
    @Query("SELECT * FROM communitySearchHistory")
    fun getSearchHistories(): List<CommunitySearchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecentSearchHistory(history: CommunitySearchHistoryEntity)

    @Query("DELETE FROM communitySearchHistory WHERE keyword = :keyword")
    fun deleteSearchHistory(keyword: String)

    @Query("DELETE FROM communitySearchHistory where id NOT IN (SELECT id from communitySearchHistory ORDER BY id DESC LIMIT 5)")
    fun deleteHistoryUntilLimitCount()
}