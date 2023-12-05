package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.time.LocalDate

class CalendarFragment  : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var recyclerViewList: ArrayList<RecyclerViewList>
    private lateinit var txtNothingHere: TextView
    private lateinit var viewPager: ViewPager2

    override fun onDeleteClick(position: Int) {
        recyclerViewList.removeAt(position)
        recyclerViewAdapter.notifyItemRemoved(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        // Initialize your views here
        var viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // Set the orientation to horizontal
        var calendarMY: LinearLayout? = view.findViewById(R.id.calendarMY)
        val (dates, todayPageIndex) = generateDatesForMonth()
        val chunks = dates.chunked(7) // Split the dates into chunks of 7
        val pagerAdapter = ViewPagerAdapter(chunks)
        var calendarMonth: TextView? = view.findViewById(R.id.calendarMonth)
        var calendarYear: TextView? = view.findViewById(R.id.calendarYear)
        txtNothingHere = view.findViewById(R.id.txtNothingHere)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Set the current month and year
        val currentDate = LocalDate.now()
        calendarMonth?.text = currentDate.month.toString()
        calendarYear?.text = currentDate.year.toString()

        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(todayPageIndex, false)

        var isTabularView = false

        calendarMY?.setOnClickListener {
            isTabularView = !isTabularView
            (viewPager.adapter as? ViewPagerAdapter)?.let { adapter ->
                adapter.isTabularView = isTabularView
                adapter.notifyDataSetChanged()
            }
        }

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

        // Check if the RecyclerView is empty and show/hide the TextView accordingly
        if (recyclerViewList.isEmpty()) {
            txtNothingHere.visibility = View.VISIBLE
        } else {
            txtNothingHere.visibility = View.GONE
        }
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