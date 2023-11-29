package com.codingwithme.meowbottomnavigationbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimeAdapter(private val timeList: ArrayList<TimeItem>) :
    RecyclerView.Adapter<TimeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTimeB: TextView = itemView.findViewById(R.id.txtTimeB)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTimeB.text = timeList[position].time
    }

    override fun getItemCount() = timeList.size
}
