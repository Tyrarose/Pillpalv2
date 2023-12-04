package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class InteractionResultFragment : Fragment() {

    private lateinit var drug1: String
    private lateinit var drug2: String

    companion object {
        fun newInstance(drug1: String, drug2: String): InteractionResultFragment {
            val fragment = InteractionResultFragment()
            val args = Bundle()
            args.putString("drug1", drug1)
            args.putString("drug2", drug2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            drug1 = it.getString("drug1", "")
            drug2 = it.getString("drug2", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interaction_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access views in your fragment layout and update them as needed
        val drugOneTextView = view.findViewById<TextView>(R.id.drugOne)
        val drugTwoTextView = view.findViewById<TextView>(R.id.drugTwo)

        // Set the drug names received as parameters
        drugOneTextView.text = drug1
        drugTwoTextView.text = drug2

        val checkNewPaitbtn = view.findViewById<ImageButton>(R.id.imageArrowleft)
        checkNewPaitbtn.setOnClickListener {
            // Create an instance of InteractionResultFragment with drug names
            val fragment = DrugdrugFragment()

            // Navigate to InteractionResultFragment
            val fragmentManager = parentFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_interaction_result, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun calculateInteractionResult(drug1: String, drug2: String): String {
        val btnSeverity = requireView().findViewById<TextView>(R.id.btnSeverity)
        val txtDescription = requireView().findViewById<TextView>(R.id.txtDescription)
        val txtExtDescription = requireView().findViewById<TextView>(R.id.txtExtDescription)






        return "Interaction Result Here"
    }
}
