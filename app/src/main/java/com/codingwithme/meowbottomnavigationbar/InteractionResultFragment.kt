package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InteractionResultFragment(drug1: String, drug2: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interaction_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access views in your fragment layout and update them as needed
        val resultTextView = view.findViewById<TextView>(R.id.txtInteractionResult)
        // Set the result text based on the interaction result
        // You can retrieve the result from arguments or any other source
        resultTextView.text = "Interaction Result Here"
    }
}
