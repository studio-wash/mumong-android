package com.studiowash.mumong.data.community.search.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studiowash.mumong.data.community.search.local.entity.CommunitySearchHistoryEntity

@Dao
interface CommunitySearchHistoryDao {
    @Query("SELECT * FROM communitySearchHistory ORDER BY searchDate DESC")
    fun getSearchHistories(): List<CommunitySearchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecentSearchHistory(history: CommunitySearchHistoryEntity)

    @Query("DELETE FROM communitySearchHistory WHERE keyword = :keyword")
    fun deleteSearchHistory(keyword: String)

    @Query("DELETE FROM communitySearchHistory where searchDate NOT IN (SELECT searchDate from communitySearchHistory ORDER BY searchDate DESC LIMIT 5)")
    fun deleteHistoryUntilLimitCount()

    @Query("DELETE FROM communitySearchHistory")
    fun deleteAllHistories()
}