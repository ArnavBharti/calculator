package com.example.calculator.history

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.standard_calculator.MainActivity
import com.example.calculator.R
import com.example.calculator.databinding.ActivityHistoryBinding


class History : AppCompatActivity() {

    lateinit var binding: ActivityHistoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        initView()

        val buttonClick = findViewById<ImageView>(R.id.button_back)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val deleteClick = findViewById<ImageButton>(R.id.deleteHistory)
        deleteClick.setOnClickListener {

        }
    }

    private fun initView() {

    }
}