package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Stack
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
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
//        buttonEquals.setOnClickListener { textField.text = calculation(textField.text.toString()) }
        buttonEquals.setOnClickListener { textField.text = "Answer: " }
        buttonOpenParenthesis.setOnClickListener { textField.text = "${textField.text}(" }
        buttonCloseParenthesis.setOnClickListener { textField.text = "${textField.text})" }
        buttonDecimal.setOnClickListener { textField.text = "${textField.text}." }
        buttonAllClear.setOnClickListener { textField.text = "" }

    }

    private fun calculation(inFix: String) : String{
        val operators = listOf("+","-","*","/","^")
        val allOperators = listOf("+","-","*","/","^","(",")")

        fun stringToList(s: String): List<String> {
            val l = mutableListOf<String>()
            var current = ""
            for (i in s) {
                val j = i.toString()
                if (j in allOperators) {
                    if (current != "") { l += current }
                    l += j
                    current = ""
                } else {
                    current += j
                }
            }
            return l
        }

        fun precedenceLower(ch: String, peek: String?) : Boolean{
            when (ch) {
                "+" -> if (peek != "+" && peek != "-" && peek != "(") {
                    return true
                }
                "-" -> if (peek != "+" && peek != "-" && peek != "(") {
                    return true
                }
                "*" -> if (peek == "^" || peek == "+" || peek == "-") {
                    return true
                }
                "/" -> if (peek == "^" || peek == "+" || peek == "-") {
                    return true
                }
            }
            return false
        }

        fun basicCalc(first: Double, second: Double, operation: String) : String {
            return when (operation) {
                "+" -> (first+second).toString()
                "-" -> (first-second).toString()
                "*" -> (first*second).toString()
                "/" -> (first/second).toString()
                "^" -> (first.pow(second)).toString()
                else -> (0.0).toString()
            }
        }

        fun inFixToPostFix(inFix: String): Stack<Any> {
            val inExp = stringToList(inFix)
            val postExp = Stack<Any>()
            val stack = Stack<Any>()

            for (ch in inExp) {
                when (ch) {
                    "(" -> stack.push("(")
                    ")" -> {
                        stack.pop()
                        while (true) {
                            val popped = stack.pop()
                            if (popped == "(") {
                                break
                            }
                            postExp.push(popped)
                        }
                    }
                    in operators -> {
                        if (precedenceLower(ch, stack.peek() as String)) {
                            for (each in stack) {
                                if (precedenceLower(each as String, ch)) {
                                    stack.push(ch)
                                    break
                                }
                                val popped = stack.pop()
                                postExp.push(popped)
                            }
                        } else {
                            stack.push(ch)
                        }
                    }
                    else -> {
                        postExp.push(ch)
                    }
                }
            }
            for (i in stack) {
                val popped = stack.pop()
                postExp.push(popped)
            }
            return postExp
        }

        fun calculatePostFix(postExp: Stack<Any>) : String {
            val stack = Stack<String>()
            for (i in postExp) {
                if (i !in allOperators) {
                    stack.push(i as String?)
                } else {
                    val operator = stack.pop()
                    val popped1 = stack.pop()
                    val popped2 = stack.pop()
                    stack.push(basicCalc(popped1.toDouble(), popped2.toDouble(), operator))
                }
            }
            return if (postExp.size == 1) {
                postExp.pop().toString()
            } else {
                "Invalid Expression"
            }
        }

        return calculatePostFix(inFixToPostFix(inFix))
    }
}
