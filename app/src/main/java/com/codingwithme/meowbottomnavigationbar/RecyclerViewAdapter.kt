package com.codingwithme.meowbottomnavigationbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val recyclerViewList: ArrayList<RecyclerViewList>, private val context: Context, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()  {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_card, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(recyclerViewList[position].image)
        holder.reminderTitle.text = recyclerViewList[position].text
        holder.reminderTime.text = recyclerViewList[position].times.joinToString(", ") // Display all times
    }

    override fun getItemCount(): Int {
        return recyclerViewList.size
    }

    class ViewHolder(itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val reminderTitle: TextView = itemView.findViewById(R.id.reminderTitle)
        val reminderTime: TextView = itemView.findViewById(R.id.reminderTime) // Make sure you have this TextView in your layout
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }
    }

    fun updateItem(position: Int, newItem: RecyclerViewList) {
        recyclerViewList[position] = newItem
        notifyItemChanged(position)
    }
}
