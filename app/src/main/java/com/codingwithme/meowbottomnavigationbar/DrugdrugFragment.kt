package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DrugdrugFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_drugdrug, container, false)
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            DrugdrugFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}