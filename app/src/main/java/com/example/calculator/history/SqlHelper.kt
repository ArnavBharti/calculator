package com.example.calculator.history

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "history.db"
        private const val TBL_HISTORY = "tbl_history"
        private const val ID = "id"
        private const val EXPRESSION = "expression"
        private const val OUTPUT = "output"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTblHistory = ("CREATE TABLE $TBL_HISTORY($ID INTEGER PRIMARY KEY, $EXPRESSION TEXT, $OUTPUT TEXT)")
        p0?.execSQL(createTblHistory)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TBL_HISTORY")
        onCreate(p0)
    }

    fun insertHistory (std: HistoryModel): Long {
        val p0 = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(EXPRESSION, std.expression)
        contentValues.put(OUTPUT,std.output)

        val success = p0.insert(TBL_HISTORY, null, contentValues)
        p0.close()
        return success
    }

    fun getAllHistory(): ArrayList<HistoryModel> {
        val stdList : ArrayList<HistoryModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_HISTORY"
        val p0 = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = p0.rawQuery(selectQuery, null)
        } catch (e: java.lang.Exception) {
            p0.execSQL(selectQuery)
            e.printStackTrace()
            return ArrayList()
        }

        var id: Int
        var expression: String
        var output: String

        if (cursor.moveToFirst()) {
            do {
                val idVal: Int = cursor.getColumnIndex("id")
                val expressionVal: Int = cursor.getColumnIndex("expression")
                val outputVal: Int = cursor.getColumnIndex("output")
                id = cursor.getInt(idVal)
                expression = cursor.getString(expressionVal)
                output = cursor.getString(outputVal)

                val std = HistoryModel(id = id, expression = expression, output = output)
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList

    }
}