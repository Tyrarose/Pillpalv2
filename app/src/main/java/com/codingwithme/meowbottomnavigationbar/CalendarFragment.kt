package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CalendarFragment  : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        val btnSetReminder: ImageView = view.findViewById(R.id.btnSetReminder)
        btnSetReminder.setOnClickListener {
            val reminderFragment = ReminderFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, reminderFragment) // Replace 'fragmentContainer' with the id of your container
                .addToBackStack(null)
                .commit()
        }

        val recyclerViewList = ArrayList<RecyclerViewList>()
        recyclerViewList.add(RecyclerViewList(R.drawable.img_tabletimage, "Warfarin"))

        val context = context
        if (context != null) {
            val recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, context, this)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.addItemDecoration(SpacesItemDecoration(10))
                } else {
                }
        return view
    }

    override fun onItemClick(position: Int) {
        // Replace the fragment
        val reminderFragment = ReminderFragment.newInstance()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, reminderFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            CalendarFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
