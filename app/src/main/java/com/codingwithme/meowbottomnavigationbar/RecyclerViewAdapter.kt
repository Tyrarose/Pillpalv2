package com.codingwithme.meowbottomnavigationbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val recyclerViewList: ArrayList<RecyclerViewList>, private val context: Context, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()  {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    inner class ViewHolder(itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val reminderTitle: TextView = itemView.findViewById(R.id.reminderTitle)
        val reminderSubTitle: TextView = itemView.findViewById(R.id.reminderSubTitle)
        val reminderTime: TextView = itemView.findViewById(R.id.reminderTime)
        val reminderItemDelete: View = itemView.findViewById(R.id.reminderItemDelete)
        val reminderItemCard: FrameLayout = itemView.findViewById(R.id.reminderItemCard)
        init {
            reminderItemDelete.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }
            reminderItemCard.setOnClickListener {
                val item = recyclerViewList[adapterPosition]
                (context as MainActivity).showReminderFragment(item)
            }
        }
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_card, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(recyclerViewList[position].image)
        holder.reminderTitle.text = recyclerViewList[position].text
        holder.reminderSubTitle.text = recyclerViewList[position].subTitle
        holder.reminderTime.text = recyclerViewList[position].times.joinToString(", ")
    }

    override fun getItemCount(): Int {
        return recyclerViewList.size
    }

    fun updateItem(position: Int, newItem: RecyclerViewList) {
        recyclerViewList[position] = newItem
        notifyItemChanged(position)
    }

    fun createNewAdapter() {
        val recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, context, listener)
        // Do something with the new adapter
    }
}
