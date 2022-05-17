package com.studiowash.mumong.data.community.search.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory

@Entity(tableName = "communitySearchHistory")
data class CommunitySearchHistoryEntity(
    val keyword: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    fun toDomainModel() = CommunitySearchHistory(keyword)
}

fun CommunitySearchHistory.toEntity() = CommunitySearchHistoryEntity(keyword)