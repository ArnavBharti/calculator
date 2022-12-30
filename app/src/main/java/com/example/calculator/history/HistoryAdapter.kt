package com.example.calculator.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import java.util.*


class HistoryAdapter( private var listHistory: ArrayList<HistoryViewModel>) :
    RecyclerView.Adapter<HistoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = listHistory[position]
        holder.expression.text = history.expression
        holder.output.text = history.output
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

}




