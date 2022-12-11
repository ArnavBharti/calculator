package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textField: TextView = findViewById(R.id.mainView)
        val outputBox: TextView = findViewById(R.id.outputBox)
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
        buttonEquals.setOnClickListener { outputBox.text = calculate(textField.text.toString()) }
        buttonOpenParenthesis.setOnClickListener { textField.text = "${textField.text}(" }
        buttonCloseParenthesis.setOnClickListener { textField.text = "${textField.text})" }
        buttonDecimal.setOnClickListener { textField.text = "${textField.text}." }
        buttonAllClear.setOnClickListener { textField.text = "" }

    }

    private fun calculate(s: String): String {
        val exp: List<Any> = toRPN(s)
        val operands = Stack<Int>()
        with(operands) {
            for (c in exp) {
                if (c is Int) {
                    push(c)
                    continue
                }
                when (c) {
                    '+' -> push(pop() + pop())
                    '-' -> push(pop().let { pop() - it })
                    '*' -> push(pop() * pop())
                    '/' -> push(pop().let { pop() / it })
                }
            }
        }
        return operands.pop().toString()
    }

    val Char.weight get() = when(this) {
        '+' -> 1
        '-' -> 1
        '*' -> 2
        '/' -> 2
        '(' -> 0
        else -> TODO()
    }

    private fun toRPN(s: String): List<Any> {
        val ret = mutableListOf<Any>()
        val ops = Stack<Char>()

        var i = 0
        while (i < s.length) {
            val c = s[i]
            if (c.isDigit()) {
                var n = c - '0'
                while (++i < s.length && s[i].isDigit()) {
                    n = 10 * n + (s[i] - '0')
                }
                ret += n
                continue
            }

            when {
                c == ' ' -> { }
                c == '(' -> ops.push(c)
                c == ')' -> {
                    while (ops.peek() != '(') ret += ops.pop()
                    ops.pop()
                }
                c.isDigit() -> ret += c
                else -> {
                    while (!ops.isEmpty() && ops.peek().weight >= c.weight) {
                        ret += ops.pop()
                    }
                    ops.push(c)
                }
            }

            i++
        }
        while (!ops.isEmpty()) ret += ops.pop()
        return ret
    }

}
