package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.math.pow
import com.example.calculator.Calculate
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Calculator by Arnav Bharti"

        binding.buttonDel.setOnClickListener { binding.mainView.text = "${binding.mainView.text}".dropLast(1) }
        binding.buttonOne.setOnClickListener { binding.mainView.text = "${binding.mainView.text}1" }
        binding.buttonTwo.setOnClickListener { binding.mainView.text = "${binding.mainView.text}2" }
        binding.buttonThree.setOnClickListener { binding.mainView.text = "${binding.mainView.text}3" }
        binding.buttonFour.setOnClickListener { binding.mainView.text = "${binding.mainView.text}4" }
        binding.buttonFive.setOnClickListener { binding.mainView.text = "${binding.mainView.text}5" }
        binding.buttonSix.setOnClickListener { binding.mainView.text = "${binding.mainView.text}6" }
        binding.buttonSeven.setOnClickListener { binding.mainView.text = "${binding.mainView.text}7" }
        binding.buttonEight.setOnClickListener { binding.mainView.text = "${binding.mainView.text}8" }
        binding.buttonNine.setOnClickListener { binding.mainView.text = "${binding.mainView.text}9" }
        binding.buttonZero.setOnClickListener { binding.mainView.text = "${binding.mainView.text}0" }
        binding.buttonAddition.setOnClickListener { binding.mainView.text = "${binding.mainView.text}+" }
        binding.buttonSubtraction.setOnClickListener { binding.mainView.text = "${binding.mainView.text}-" }
        binding.buttonMultiplication.setOnClickListener { binding.mainView.text = "${binding.mainView.text}*" }
        binding.buttonDivision.setOnClickListener { binding.mainView.text = "${binding.mainView.text}/" }
        binding.buttonPower.setOnClickListener { binding.mainView.text = "${binding.mainView.text}^" }
        binding.buttonEqualsSign.setOnClickListener { binding.outputBox.text = Calculate().calculate(binding.mainView.text.toString()) }
        binding.buttonOpenParenthesis.setOnClickListener { binding.mainView.text = "${binding.mainView.text}(" }
        binding.buttonCloseParenthesis.setOnClickListener { binding.mainView.text = "${binding.mainView.text})" }
        binding.buttonDecimal.setOnClickListener { binding.mainView.text = "${binding.mainView.text}." }
        binding.buttonAllClear.setOnClickListener { binding.mainView.text = ""; binding.outputBox.text = "" }

    }



}
