package com.example.calculator.history

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//The class is extending SQLiteOpenHelper
class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE =
            "CREATE	TABLE $TABLE_HISTORY($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_EXPRESSION TEXT,$COLUMN_OUTPUT TEXT)"
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun listHistory(): ArrayList<HistoryViewModel> {
        val sql = "select * from $TABLE_HISTORY"
        val db = this.readableDatabase
        val storeContacts: ArrayList<HistoryViewModel> = ArrayList()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val expression = cursor.getString(1)
                val output = cursor.getString(2)
                storeContacts.add(HistoryViewModel(id, expression, output))
            } while (cursor.moveToNext())
        }
        cursor.close()
        storeContacts.reverse()
        storeContacts.take(50)
        return storeContacts
    }

    fun addHistory(historyViewModel: HistoryViewModel) {
        val values = ContentValues()
        values.put(COLUMN_EXPRESSION, "Expression: "+historyViewModel.expression)
        values.put(COLUMN_OUTPUT, "Answer: "+historyViewModel.output)
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
    }


    fun deleteHistory() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_HISTORY")
    }

    companion object {
        private const val DATABASE_VERSION = 5
        private const val DATABASE_NAME = "history"
        private const val TABLE_HISTORY = "history"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_EXPRESSION = "expression"
        private const val COLUMN_OUTPUT = "output"
    }
}