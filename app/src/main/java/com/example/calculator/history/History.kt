package com.example.calculator.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.standard_calculator.MainActivity
import com.example.calculator.R
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.databinding.ActivityMatrixBinding
import java.sql.SQLInput


class History : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var holder: ActivityMatrixBinding

    private lateinit var recyclerView: RecyclerView
    private var adapter: HistoryAdapter? = null

    private lateinit var sqlHelper: SqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        holder  = ActivityMatrixBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_history)
        title = "Calculator History"


        initRecyclerView()
        sqlHelper = SqlHelper(this)
        binding.buttonEqualsSign.setOnClickListener { insertHistory() }
        holder.buttonEqualsSign2.setOnClickListener { insertHistory2() }

        val buttonClick = findViewById<ImageView>(R.id.button_back)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val deleteClick = findViewById<ImageButton>(R.id.deleteHistory)
        deleteClick.setOnClickListener {

        }

    }

    private fun insertHistory() {
        val expression = binding.mainView.text.toString()
        val output = binding.outputBox.text.toString()
        if (expression.isEmpty() || output.isEmpty()) {

        } else {
            val std = HistoryModel(expression = expression, output = output)
            val status = sqlHelper.insertHistory(std)
        }
    }

    private fun insertHistory2() {
        val expression2 = holder.mainView2.text.toString()
        val output2 = holder.outputBox2.text.toString()
        if (expression2.isEmpty() || output2.isEmpty()) {

        } else {
            val std = HistoryModel(expression = expression2, output = output2)
            val status = sqlHelper.insertHistory(std)
        }
    }

    private fun getHistory() {
        val stdList = sqlHelper.getAllHistory()
        Log.e("pppp", "${stdList.size}")

        adapter?.addItems(stdList)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter()
        recyclerView.adapter = adapter
    }

}