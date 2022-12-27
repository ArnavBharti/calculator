package com.example.calculator.history.history_database.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryData (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name = "Expression") val Expression: String,
    @NonNull @ColumnInfo(name = "Output") val Output: String
        )