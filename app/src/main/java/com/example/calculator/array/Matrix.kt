package com.example.calculator.array

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.calculator.history.History
import com.example.calculator.standard_calculator.MainActivity
import com.example.calculator.R

class Matrix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)

        val buttonClick = findViewById<Button>(R.id.buttonMatrix2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val historyClick = findViewById<ImageButton>(R.id.buttonHistory2)
        historyClick.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }
}