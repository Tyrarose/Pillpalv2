package com.codingwithme.meowbottomnavigationbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DateAdapter(private val dates: List<DateItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SELECTED = 0
        private const val TYPE_UNSELECTED = 1
    }
    override fun getItemViewType(position: Int): Int {
        return if (dates[position].isSelected) TYPE_SELECTED else TYPE_UNSELECTED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SELECTED) {
            SelectedDateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_date_clicked, parent, false))
        } else {
            DateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false))
        }
    }
    private var selectedPosition = -1  // Add this line
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dateItem = dates[position]
        if (holder is DateViewHolder) {
            holder.bind(dateItem)
            holder.itemView.setOnClickListener {
                val pos = holder.adapterPosition
                if (selectedPosition != -1) {
                    // Deselect the previously selected item
                    dates[selectedPosition].isSelected = false
                    notifyItemChanged(selectedPosition)
                }
                // Select the clicked item
                dateItem.isSelected = true
                notifyItemChanged(pos)
                selectedPosition = pos
            }
        } else if (holder is SelectedDateViewHolder) {
            holder.bind(dateItem)
            holder.itemView.setOnClickListener {
                val pos = holder.adapterPosition
                // Deselect the clicked item
                dateItem.isSelected = false
                notifyItemChanged(pos)
                selectedPosition = -1
            }
        }
    }
    override fun getItemCount() = dates.size
    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dateItem: DateItem) {
            val txtEighteen: TextView = itemView.findViewById(R.id.txtEighteen)
            val txtMon: TextView = itemView.findViewById(R.id.txtMon)

            txtEighteen.text = dateItem.date
            txtMon.text = dateItem.day
        }
    }
    inner class SelectedDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dateItem: DateItem) {
            val txtNineteen: TextView = itemView.findViewById(R.id.txtNineteen)
            val txtTue: TextView = itemView.findViewById(R.id.txtTue)

            txtNineteen.text = dateItem.date
            txtTue.text = dateItem.day
        }
    }
}