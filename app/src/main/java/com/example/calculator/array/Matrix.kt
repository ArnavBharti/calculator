package com.example.calculator.array

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.calculator.history.History
import com.example.calculator.standard_calculator.MainActivity
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMatrixBinding
import com.example.calculator.history.DBHelper
import com.example.calculator.history.HistoryViewModel

class Matrix : AppCompatActivity() {
    lateinit var binding: ActivityMatrixBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatrixBinding.inflate(layoutInflater)


        setContentView(binding.root)

        title = "Array Calculator"


        val buttonClick = findViewById<Button>(R.id.buttonNormal)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val historyClick = findViewById<ImageButton>(R.id.buttonHistory2)
        historyClick.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        binding.buttonDel2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}".dropLast(1)
        }
        binding.buttonOne2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}1"
        }
        binding.buttonTwo2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}2"
        }
        binding.buttonThree2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}3"
        }
        binding.buttonFour2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}4"
        }
        binding.buttonFive2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}5"
        }
        binding.buttonSix2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}6"
        }
        binding.buttonSeven2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}7"
        }
        binding.buttonEight2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}8"
        }
        binding.buttonNine2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}9"
        }
        binding.buttonZero2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}0"
        }
        binding.buttonAddition2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}+"
        }
        binding.buttonSubtraction2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}-"
        }
        binding.buttonMultiplication2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}*"
        }
        binding.buttonDivision2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}/"
        }
        binding.buttonPower2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text},"
        }
        binding.buttonEqualsSign2.setOnClickListener {
            binding.outputBox2.text =
                ArrayCalculation().calculate(binding.mainView2.text.toString())

            if (binding.outputBox2.text.toString() != "Error") {
                var mDatabase: DBHelper?
                mDatabase = DBHelper(this)
                val newHistory = HistoryViewModel(
                    binding.mainView2.text.toString(),
                    binding.outputBox2.text.toString()
                )
                mDatabase.addHistory(newHistory)
            }


        }
        binding.buttonOpenParenthesis2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}("
        }
        binding.buttonCloseParenthesis2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text})"
        }
        binding.buttonDecimal2.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}."
        }
        binding.buttonAllClear2.setOnClickListener {
            binding.mainView2.text = ""; binding.outputBox2.text = ""
        }
        binding.buttonHistory2.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
        binding.buttonNormal.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSquareOpen.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}["
        }
        binding.buttonSquareClosed.setOnClickListener {
            binding.mainView2.text = "${binding.mainView2.text}]"
        }


    }
}