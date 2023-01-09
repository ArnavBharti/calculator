package com.example.calculator.standard_calculator

import java.util.*
import kotlin.math.pow

class Calculate {

    private fun performOperation(operands: Stack<Double>, operations: Stack<Char>): Double? {
        val a: Double = operands.pop()
        val b: Double = operands.pop()

        if (a == 0.0 && operations.peek() == '/') {
            return null
        }

        when (operations.pop()) {
            '+' -> return (a + b)
            '-' -> return (b - a)
            '*' -> return (a * b)
            '/' -> return (b / a)
            '^' -> return b.pow(a)
        }

        return null
    }

    private fun precedence(c: Char): Int {
        when (c) {
            '+', '-' -> return 1
            '*', '/' -> return 2
            '^' -> return 3
        }
        return -1
    }

    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '/' || c == '*' || c == '^'
    }

    fun calculate(exp: String): String {
        try {
            val operands: Stack<Double> = Stack()
            val operations: Stack<Char> = Stack()
            var i = 0
            while (i < exp.length) {
                var c = exp[i]
                if (Character.isDigit(c) || c == '.') {
                    var num = ""
                    while (Character.isDigit(c) || c == '.') {
                        if (c == '.') {
                            num += "."
                        } else {
                            num += c
                        }
                        i++
                        c = if (i < exp.length) {
                            exp[i]
                        } else {
                            break
                        }
                    }
                    i--
                    operands.push(num.toDouble())
                } else if (c == '(') {
                    operations.push(c)
                } else if (c == ')') {
                    while (!operations.isEmpty() && operations.peek() != '(') {
                        val output = performOperation(operands, operations)
                        operands.push(output)
                    }
                    operations.pop()
                } else if (isOperator(c)) {
                    if (c == '-' && i == 0) {
                        var num = "-"
                        i++
                        if (i < exp.length) {
                            c = exp[i]
                        }
                        while ((Character.isDigit(c) || c == '.') || c == '-' && isOperator(exp[i - 1])) {
                            if (c == '.') {
                                num += "."
                            } else {
                                num += c
                            }
                            i++
                            c = if (i < exp.length) {
                                exp[i]
                            } else {
                                break
                            }
                        }
                        i--
                        operands.push(num.toDouble())
                    } else if ((c == '-' && isOperator(exp[i - 1])) || (c == '-' && exp[i - 1] in listOf(
                            '(',
                            ')'
                        ))
                    ) {
                        var num = "-"
                        i++
                        if (i < exp.length) {
                            c = exp[i]
                        }
                        while (Character.isDigit(c) || c == '.') {
                            if (c == '.') {
                                num += "."
                            } else {
                                num += c
                            }
                            i++
                            c = if (i < exp.length) {
                                exp[i]
                            } else {
                                break
                            }
                        }
                        i--
                        operands.push(num.toDouble())
                    } else {

                        while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                            val output = performOperation(operands, operations)
                            operands.push(output)
                        }
                        operations.push(c)
                    }

                }
                i++
            }
            while (!operations.isEmpty() && operands.size >= 1) {
                val output = performOperation(operands, operations)
                operands.push(output)
            }
            return (operands.pop().toString())
        } catch (e: EmptyStackException) {
            return ("Error")
        } catch (e: java.lang.NullPointerException) {
            return ("Error")
        } catch (e: NotImplementedError) {
            return "Error"
        } catch (e: NumberFormatException) {
            return "Error"
        }
    }
}