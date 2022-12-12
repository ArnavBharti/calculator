package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.math.pow

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
        buttonEquals.setOnClickListener {
            try {
                outputBox.text = evaluate(textField.text.toString()).toString()
            } catch (e: EmptyStackException) {
                outputBox.text = "error"
            } finally {
                outputBox.text = "error"
            }


        }
        buttonOpenParenthesis.setOnClickListener { textField.text = "${textField.text}(" }
        buttonCloseParenthesis.setOnClickListener { textField.text = "${textField.text})" }
        buttonDecimal.setOnClickListener { textField.text = "${textField.text}." }
        buttonAllClear.setOnClickListener { textField.text = "" }

    }



    fun evaluate(exp: String): Double {
        val operands: Stack<Double> = Stack() //Operand stack
        val operations: Stack<Char> = Stack() //Operator stack
        var i = 0
        while (i < exp.length) {
            var c = exp[i]
            if (Character.isDigit(c)) //check if it is number
            {
                //Entry is Digit, and it could be greater than a one-digit number
                var num = 0.0
                while (Character.isDigit(c)) {
                    num = num * 10 + (c - '0')
                    i++
                    c = if (i < exp.length) {
                        exp[i]
                    } else {
                        break
                    }
                }
                i--
                operands.push(num)
            } else if (c == '(') {
                operations.push(c) //push character to operators stack
            } else if (c == ')') {
                while (!operations.isEmpty() && operations.peek() != '(') {
                    val output = performOperation(operands, operations)
                    if (output != null) { operands.push(output) }//push result back to stack
                }
                operations.pop()
            } else if (isOperator(c)) {
                while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                    val output = performOperation(operands, operations)
                    if (output != null) { operands.push(output) } //push result back to stack
                }
                operations.push(c)
                //push the current operator to stack
            }
            i++
        }
        val output = performOperation(operands, operations)
        if (output != null) {
            operands.push(output)
        } //push final result back to stack

        return operands.pop()
    }

    fun precedence(c: Char): Int {
        when (c) {
            '+', '-' -> return 1
            '*', '/' -> return 2
            '^' -> return 3
        }
        return -1
    }

    fun performOperation(operands: Stack<Double>, operations: Stack<Char>): Double? {
        val a: Double = operands.pop()
        val b: Double = operands.pop()
//    val outputBox: TextView = findViewById(R.id.outputBox)

        when (operations.pop()) {
            '+' -> return (a + b)
            '-' -> return (b - a)
            '*' -> return (a * b)
            '/' -> {
                if (a == 0.0) {
                    print("found")
//                outputBox.text = "Cannot divide by zero"
                    return null
                }
                return (b / a)
            }
            '^' -> return a.pow(b)
        }
//    outputBox.text =  "Error, Try Again"
        return null
    }

    fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '/' || c == '*' || c == '^'
    }


}
