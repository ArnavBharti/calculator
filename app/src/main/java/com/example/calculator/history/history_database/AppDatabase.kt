package com.example.calculator.history.history_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculator.history.history_database.data.HistoryDao
import com.example.calculator.history.history_database.data.HistoryData

@Database(entities = arrayOf(HistoryData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/history.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}