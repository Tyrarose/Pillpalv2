package com.codingwithme.meowbottomnavigationbar

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class MyAdapter(context: Context, resource: Int, private var originalItems: List<Item>) :
    ArrayAdapter<Item>(context, resource, originalItems) {

    private var filteredItems: List<Item> = originalItems
    private var searchString: String = ""
    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getItem(position: Int): Item? {
        return filteredItems[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
            viewHolder = ViewHolder(view.findViewById(android.R.id.text1))
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val itemName = filteredItems[position].name
        val startIndex = itemName.indexOf(searchString, ignoreCase = true)
        val endIndex = startIndex + searchString.length

        if (startIndex != -1) {
            val spannable = SpannableString(itemName)
            spannable.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            viewHolder.text.text = spannable
        } else {
            viewHolder.text.text = itemName
        }

        return view
    }

    fun filter(text: String) {
        filteredItems = originalItems.filter { it.name.toLowerCase(Locale.getDefault()).startsWith(text.toLowerCase(Locale.getDefault())) }.take(3)
        notifyDataSetChanged()
    }

    fun filterOriginalItems() {
        filteredItems = originalItems
        notifyDataSetChanged()
    }

    private class ViewHolder(val text: TextView)
}
