package com.example.calculator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.history.history_database.data.HistoryDao
import com.example.calculator.history.history_database.data.HistoryData

class HistoryViewModel(private val historyDao: HistoryDao): ViewModel() {
    fun fullSchedule(): List<HistoryData> = historyDao.getAll()
}

class HistoryViewModelFactory(
    private val historyDao: HistoryDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(historyDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}