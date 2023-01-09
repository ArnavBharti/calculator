package com.example.calculator.array

import com.example.calculator.standard_calculator.Calculate

class ArrayCalculation {
    fun calculate(exp: String): String {
        try {
            var lst1 = mutableListOf<String>()
            var lst2 = mutableListOf<String>()
            var counter = 0
            if (exp[counter] == '[') {
                counter++
                var str = ""
                while (exp[counter] != ']' && counter < exp.length) {
                    str += exp[counter].toString()
                    counter++
                }
                if (counter >= exp.length) {
                    return "Error"
                } else {
                    val lst = str.split(',')
                    for (each in lst) {
                        lst1.add(Calculate().calculate(each))
                    }
                    counter++
                    if (exp[counter] == '*') {
                        counter++
                        if (exp[counter] == '[') {
                            counter++
                            str = ""
                            while (exp[counter] != ']' && counter < exp.length) {
                                str += exp[counter].toString()
                                counter++
                            }
                            if (counter >= exp.length) {
                                return "Error"
                            } else {
                                val lst0 = str.split(',')
                                for (each in lst0) {
                                    lst2.add(Calculate().calculate(each))
                                }
                            }
                        } else {
                            return "Error"
                        }
                    } else {
                        return "Error"
                    }
                }
            } else {
                return "Error"
            }

            return if (lst1.size == lst2.size) {
                var listAnswer = mutableListOf<Double>()
                for (i in 0 until lst1.size) {
                    listAnswer.add(lst1[i].toDouble() * lst2[i].toDouble())
                }
                listAnswer.toString()
            } else {
                "Error"
            }
        } catch (e: NumberFormatException) {
            return "Error"
        }
    }


}