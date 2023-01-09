package com.example.calculator.standard_calculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.array.Matrix
import java.util.*
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.history.DBHelper
import com.example.calculator.history.History
import com.example.calculator.history.HistoryViewModel
import com.example.calculator.notification.NotificationMain

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    lateinit var binding: ActivityMainBinding
    lateinit var holder:
            ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Standard Calculator"
        binding.buttonDel.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}".dropLast(1)
        }
        binding.buttonOne.setOnClickListener { binding.mainView.text = "${binding.mainView.text}1" }
        binding.buttonTwo.setOnClickListener { binding.mainView.text = "${binding.mainView.text}2" }
        binding.buttonThree.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}3"
        }
        binding.buttonFour.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}4"
        }
        binding.buttonFive.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}5"
        }
        binding.buttonSix.setOnClickListener { binding.mainView.text = "${binding.mainView.text}6" }
        binding.buttonSeven.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}7"
        }
        binding.buttonEight.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}8"
        }
        binding.buttonNine.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}9"
        }
        binding.buttonZero.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}0"
        }
        binding.buttonAddition.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}+"
        }
        binding.buttonSubtraction.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}-"
        }
        binding.buttonMultiplication.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}*"
        }
        binding.buttonDivision.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}/"
        }
        binding.buttonPower.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}^"
        }
        binding.buttonEqualsSign.setOnClickListener {
            binding.outputBox.text = Calculate().calculate(binding.mainView.text.toString())

            if (binding.outputBox.text.toString() != "Error") {
                var mDatabase: DBHelper?
                mDatabase = DBHelper(this)
                val newHistory = HistoryViewModel(
                    binding.mainView.text.toString(),
                    binding.outputBox.text.toString()
                )
                mDatabase.addHistory(newHistory)
            }

        }
        binding.buttonOpenParenthesis.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}("
        }
        binding.buttonCloseParenthesis.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text})"
        }
        binding.buttonDecimal.setOnClickListener {
            binding.mainView.text = "${binding.mainView.text}."
        }
        binding.buttonAllClear.setOnClickListener {
            binding.mainView.text = ""; binding.outputBox.text = ""
        }
        binding.buttonHistory.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
        binding.buttonMatrix.setOnClickListener {
            val intent = Intent(this, Matrix::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, NotificationMain::class.java)
            startActivity(intent)
        }
    }
}
