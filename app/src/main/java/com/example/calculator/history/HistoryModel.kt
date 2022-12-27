package com.example.calculator.history

import kotlin.random.Random

data class HistoryModel (var id: Int = getAutoId(),
                         var expression: String = "",
                         var output: String = ""
                         ) {
    companion object {
        fun getAutoId (): Int {
            return Random.nextInt()
        }
    }


}