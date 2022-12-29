package com.example.calculator.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R


class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var stdList: ArrayList<HistoryModel> = ArrayList()

    fun addItems(items:ArrayList<HistoryModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder = HistoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std,parent,false)
    )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val std = stdList[position]

        holder.bindView(std)
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class HistoryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var exp = view.findViewById<TextView>(R.id.expressionView)
        private var out = view.findViewById<TextView>(R.id.outputView)
        fun bindView(std: HistoryModel) {
            exp.text = std.expression.toString()
            out.text = std.output.toString()
        }
    }

}