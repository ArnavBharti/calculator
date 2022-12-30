package com.example.calculator.history

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class History : AppCompatActivity() {
    private var mDatabase: DBHelper? = null
    private var allHistory: ArrayList<HistoryViewModel> = ArrayList()
    private var mAdapter: HistoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.calculator.R.layout.activity_history)
        val historyView =
            findViewById<View>(com.example.calculator.R.id.recycler_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this)
        historyView.layoutManager = linearLayoutManager
        historyView.setHasFixedSize(true)
        mDatabase = DBHelper(this)
        allHistory = mDatabase!!.listHistory()
        val historyAdapter = HistoryAdapter(allHistory)
        if (allHistory.size > 0) {
            historyView.visibility = View.VISIBLE
            mAdapter = HistoryAdapter(allHistory)
            historyView.adapter = mAdapter
        }

        findViewById<ImageButton>(com.example.calculator.R.id.deleteHistory).setOnClickListener {
            mDatabase!!.deleteHistory()
            historyAdapter.notifyDataSetChanged()
            allHistory = mDatabase!!.listHistory()
            mAdapter = HistoryAdapter(allHistory)

            historyView.adapter = mAdapter

        }
        findViewById<ImageView>(com.example.calculator.R.id.button_back).setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDatabase != null) {
            mDatabase!!.close()
        }
    }


}
