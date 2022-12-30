package com.example.calculator.history

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var expression: TextView
    var output: TextView

    init {
        expression = itemView.findViewById<View>(R.id.expressionView) as TextView
        output = itemView.findViewById<View>(R.id.outputView) as TextView

    }
}
