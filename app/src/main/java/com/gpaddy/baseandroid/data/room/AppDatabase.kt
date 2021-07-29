package com.gpaddy.baseandroid.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gpaddy.baseandroid.data.model.db.HistoryModel

@Database(version = 2, exportSchema = false,
    entities = [HistoryModel::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
    companion object {
        private val NAME_DB="AppName"
        @Volatile
        private var instance: AppDatabase? = null

        fun create(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NAME_DB).allowMainThreadQueries().build()
        }

    }

}