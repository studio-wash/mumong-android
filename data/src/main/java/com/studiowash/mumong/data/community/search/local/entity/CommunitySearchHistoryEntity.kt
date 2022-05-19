package com.studiowash.mumong.data.community.search.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.studiowash.mumong.domain.community.entity.CommunitySearchHistory
import java.util.*

@TypeConverters(DateConverter::class)
@Entity(tableName = "communitySearchHistory")
data class CommunitySearchHistoryEntity(
    @PrimaryKey val keyword: String,
    val searchDate: Date = Date()
) {
    fun toDomainModel() = CommunitySearchHistory(keyword)
}

fun CommunitySearchHistory.toEntity() = CommunitySearchHistoryEntity(keyword)

class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}
