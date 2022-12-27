package com.example.calculator.history.history_database.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historydata")
    fun getAll(): List<HistoryData>
}