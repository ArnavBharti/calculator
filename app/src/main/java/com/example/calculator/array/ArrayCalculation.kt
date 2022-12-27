package com.example.calculator.array

import android.widget.TextView
import com.example.calculator.standard_calculator.Calculate

class ArrayCalculation {
    private fun calculate (command: TextView, screen: TextView, output: TextView) {
        while (true) {
            command.text = "Enter length of array"
            try {
                val lengthOfArray = Calculate().calculate(screen.text.toString()).toDouble()
            } catch (e: java.lang.NumberFormatException) {
                output.text = "Error"
                continue
            }

        }


    }
}