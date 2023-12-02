package com.codingwithme.meowbottomnavigationbar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val chunks: List<List<DateItem>>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    var isTabularView = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chunk = chunks[position]
        val adapter = DateAdapter(chunk)
        holder.recyclerView.adapter = adapter
        holder.recyclerView.layoutManager = if (isTabularView) {
            // Use a GridLayoutManager with 7 columns for a tabular view
            GridLayoutManager(holder.itemView.context, 7)
        } else {
            // Use a LinearLayoutManager for a list view
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun getItemCount() = chunks.size
}