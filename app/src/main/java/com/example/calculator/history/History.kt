package com.example.calculator.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.databinding.ActivityMatrixBinding


class History : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var holder: ActivityMatrixBinding
    private lateinit var dataBase: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        holder  = ActivityMatrixBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_history)
        title = "Calculator History"

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<HistoryViewModel>()



        findViewById<ImageButton>(R.id.deleteHistory).setOnClickListener {
            data.clear()
            var adapter = HistoryAdapter(data)
            recyclerView.adapter = adapter
        }


        val button_back = findViewById<ImageView>(R.id.button_back)
        button_back.setOnClickListener {
            onBackPressed()
        }
}}