package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Stack
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textField: TextView = findViewById(R.id.mainView)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val buttonFive: Button = findViewById(R.id.buttonFive)
        val buttonSix: Button = findViewById(R.id.buttonSix)
        val buttonSeven: Button = findViewById(R.id.buttonSeven)
        val buttonEight: Button = findViewById(R.id.buttonEight)
        val buttonNine: Button = findViewById(R.id.buttonNine)
        val buttonZero: Button = findViewById(R.id.buttonZero)
        val buttonAdd: Button = findViewById(R.id.buttonAddition)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtraction)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiplication)
        val buttonDivide: Button = findViewById(R.id.buttonDivision)
        val buttonPower: Button = findViewById(R.id.buttonPower)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)
        val buttonEquals: Button = findViewById(R.id.buttonEqualsSign)
        val buttonOpenParenthesis: Button = findViewById(R.id.buttonOpenParenthesis)
        val buttonCloseParenthesis: Button = findViewById(R.id.buttonCloseParenthesis)
        val buttonAllClear: Button = findViewById(R.id.buttonAllClear)

        buttonOne.setOnClickListener { textField.text = "${textField.text}1" }
        buttonTwo.setOnClickListener { textField.text = "${textField.text}2" }
        buttonThree.setOnClickListener { textField.text = "${textField.text}3" }
        buttonFour.setOnClickListener { textField.text = "${textField.text}4" }
        buttonFive.setOnClickListener { textField.text = "${textField.text}5" }
        buttonSix.setOnClickListener { textField.text = "${textField.text}6" }
        buttonSeven.setOnClickListener { textField.text = "${textField.text}7" }
        buttonEight.setOnClickListener { textField.text = "${textField.text}8" }
        buttonNine.setOnClickListener { textField.text = "${textField.text}9" }
        buttonZero.setOnClickListener { textField.text = "${textField.text}0" }
        buttonAdd.setOnClickListener { textField.text = "${textField.text}+" }
        buttonSubtract.setOnClickListener { textField.text = "${textField.text}-" }
        buttonMultiply.setOnClickListener { textField.text = "${textField.text}*" }
        buttonDivide.setOnClickListener { textField.text = "${textField.text}/" }
        buttonPower.setOnClickListener { textField.text = "${textField.text}^" }
        buttonEquals.setOnClickListener { calculation() }
        buttonOpenParenthesis.setOnClickListener { textField.text = "${textField.text}(" }
        buttonCloseParenthesis.setOnClickListener { textField.text = "${textField.text})" }
        buttonDecimal.setOnClickListener { textField.text = "${textField.text}." }
        buttonAllClear.setOnClickListener { textField.text = "" }

    }

//    private fun calculation() : String {
//        fun roundOff(precision: Int, value: Double): Double {
//            val offset = 10.0 * precision
//            return round(value * offset) / offset
//        }
//
//        val precision = 3
//        var solution = 0.0
//
//        fun precedenceLower(ch: Char, peek: Char?) : Boolean{
//            when (ch) {
//                '+' -> if (peek != '+' && peek != '-' && peek != '(') {
//                    return true
//                }
//                '-' -> if (peek != '+' && peek != '-' && peek != '(') {
//                    return true
//                }
//                '*' -> if (peek == '^' || peek == '+' || peek == '-') {
//                    return true
//                }
//                '/' -> if (peek == '^' || peek == '+' || peek == '-') {
//                    return true
//                }
//                else -> {
//                    return false
//                }
//            }
//        }
//
//        fun inFixtoPostFix(inExp: String) : String {
//            var postExp = mutableListOf<String>()
//            var stack = Stack<Char>()
//
//            for (ch in inExp) {
//                val operator = listOf<Char>('+','-','*','/','^')
//                when (ch) {
//                    '(' -> stack.push('(')
//                    ')' -> {
//                        for (i in stack) {
//                            val popped = stack.pop()
//                            if (popped == '(') {
//                                break
//                            }
//                            postExp.add(-1, popped)
//                        }
//                    }
//                    in operator -> {
//                        if (precedenceLower(ch, stack.peek())) {
//                            for (i in stack) {
//                                val popped = stack.pop()
//                                postExp.add(-1,popped)
//                                if (precedenceLower(i, ch)) {
//                                    break
//                                }
//                            }
//                        } else {
//                            stack.push(ch)
//                        }
//                    }
//                    else -> {postExp.add(-1, ch)}
//                }
//            }
//
//            for (i in stack) {
//                val popped = stack.pop()
//                postExp.add(-1,popped)
//            }
//            return postExp.joinToString()
//        }
//
//
//
//        return roundOff(precision, solution).toString()
//    }
}
