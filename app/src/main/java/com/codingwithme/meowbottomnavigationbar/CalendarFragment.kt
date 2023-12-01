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
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter // Declare the adapter as a class variable
    private lateinit var recyclerViewList: ArrayList<RecyclerViewList> // Declare the list as a class variable

    override fun onDeleteClick(position: Int) {
        recyclerViewList.removeAt(position)
        recyclerViewAdapter.notifyItemRemoved(position)
    }

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

        recyclerViewList = ArrayList<RecyclerViewList>()

        val context = context
        if (context != null) {
            recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, context, this) // Initialize the adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.addItemDecoration(SpacesItemDecoration(10))
        } else {
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerViewList = (activity as MainActivity).getReminderList() // Get the updated list
        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, requireContext(), this) // Create a new adapter with the updated list
        recyclerView.adapter = recyclerViewAdapter // Set the new adapter
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