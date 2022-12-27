package com.example.calculator

import android.app.Application
import com.example.calculator.history.history_database.AppDatabase

class HistoryApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}