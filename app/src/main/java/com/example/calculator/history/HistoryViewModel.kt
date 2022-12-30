package com.example.calculator.history

class HistoryViewModel {
    var id = 0
    var expression: String
    var output: String

    constructor(expression: String, output: String) {
        this.expression = expression
        this.output = output
    }

    constructor(id: Int, expression: String, output: String) {
        this.id = id
        this.expression = expression
        this.output = output
    }
}