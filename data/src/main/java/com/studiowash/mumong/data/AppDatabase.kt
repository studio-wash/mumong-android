package com.studiowash.mumong.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studiowash.mumong.data.community.search.local.dao.CommunitySearchHistoryDao
import com.studiowash.mumong.data.community.search.local.entity.CommunitySearchHistoryEntity

@Database(entities = [CommunitySearchHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun communitySearchHistoryDao(): CommunitySearchHistoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createInstance(context).also { INSTANCE = it }
            }
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}