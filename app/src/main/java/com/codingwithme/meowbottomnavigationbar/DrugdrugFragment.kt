package com.codingwithme.meowbottomnavigationbar

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class DrugdrugFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: MyAdapter
    private lateinit var items: List<Item>
    private var buttonCount = 0
    private val createdButtons = mutableListOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drugdrug, container, false)
        val buttonContainer = view.findViewById<LinearLayout>(R.id.buttonContainer)
        val listView = view.findViewById<ListView>(R.id.listView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)


        items = listOf(
            Item("Warfarin"),
            Item("Acetaminophen [Tylenol]"),
            Item("Abacavir"),
            Item("Abciximab"),
            Item("Calcium acetate"),
            Item("Cobicistat"),
            Item("Dolutegravir"),
            Item("Deferasirox"),
            Item("Lamivudine"),
            Item("Mannitol"),
        )


        adapter = MyAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter


        listView.visibility = View.GONE


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    adapter.filterOriginalItems()
                    listView.visibility = View.GONE
                } else {
                    adapter.filter(newText)
                    listView.visibility = View.VISIBLE
                }
                return true
            }
        })

        val checkInteractionsBtn = view.findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = view.findViewById<Button>(R.id.btnClear)
        val loadExampleBtn = view.findViewById<Button>(R.id.btnLoadExample)

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItemName = adapter?.getItem(position)?.name
            if (!selectedItemName.isNullOrBlank() && buttonCount < 2) {
                createButton(selectedItemName)
                searchView.setQuery("", false)
                clearBtn.isEnabled = true
            }
        }


        checkInteractionsBtn.isEnabled = false
        clearBtn.isEnabled = false


        loadExampleBtn?.setOnClickListener {
            loadExampleButtons()
        }

        clearBtn?.setOnClickListener {
            if (buttonCount < 2) {
                clearButtons()
                clearBtn.isEnabled = false
            }
            checkInteractionsBtn.isEnabled = false
        }


        val interactionSample = view.findViewById<LinearLayout>(R.id.drugdrugResults)
        val threeButtons = view.findViewById<LinearLayout>(R.id.threeButtonsGroup)
        checkInteractionsBtn?.setOnClickListener {
            interactionSample.visibility = View.VISIBLE
            threeButtons.visibility = View.GONE

        }

        // Replace "your_file_path.csv" with the actual path to your CSV file
        val filePath = "your_file_path.csv"
//        readCsvFile(filePath)



        return view
    } /////////////////// END OF ONCREATE =================


    /////////////////// START OF MY FUNCTIONS =================

    private fun createButton(buttonText: String) {
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        if (buttonCount < 2) {
            val newButton = Button(requireContext())
            newButton.text = buttonText

            val newButtonDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_15)
            newButton.background = newButtonDrawable
            newButton.setTextAppearance(requireContext(), R.style.txtPoppinsbold16_1)

            val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
            buttonContainer?.addView(newButton)
            createdButtons.add(newButton)
            buttonCount++

            val params1 = newButton.layoutParams as ViewGroup.MarginLayoutParams
            params1.bottomMargin = 20
            params1.rightMargin = 50
            params1.leftMargin = 50

            newButton.layoutParams = params1

            val iconDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.img_minuscircle)
            newButton.setCompoundDrawablesWithIntrinsicBounds(iconDrawable, null, null, null)

            newButton.setOnClickListener {
                removeButton(newButton)
            }
        }

        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)

        if (buttonCount == 2) {
            txtAddtwodrugs?.text = "Two drugs added"
            txtAddtwodrugs?.setTypeface(null, Typeface.BOLD)

            checkInteractionsBtn.isEnabled = true

            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.whitetext)

            checkInteractionsBtn?.setOnClickListener {
                checkInteractions()
            }
        }

        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)
        clearBtn?.setOnClickListener {
            buttonCount == 0
            clearButtons()
        }
    }


    private fun removeButton(button: Button) {
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)

        buttonContainer?.removeView(button)
        createdButtons.remove(button)

        txtAddtwodrugs?.setText(R.string.lbl_add_two_drugs)
        txtAddtwodrugs?.setTypeface(null, Typeface.NORMAL)

        buttonCount--

        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)


        if (buttonCount == 1){
            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)
        }

        if(buttonCount == 0){
            val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
            clearBtn.background = clearBtnDrawable
            clearBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)
        }

        checkInteractionsBtn?.isEnabled = false
        clearBtn?.isEnabled = true
    }




    private fun loadExampleButtons() {
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)

        buttonCount = 2
        if (buttonCount == 2) {
            txtAddtwodrugs?.text = "Two drugs added"
            txtAddtwodrugs?.setTypeface(null, Typeface.BOLD)

            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.whitetext)
        }

        clearButtons()
        checkInteractions()

        for (i in 0 until 2) {
            if (i < items.size) {
                createButton(items[i].name)
            }
        }


        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)

        checkInteractionsBtn.isEnabled = true
        clearBtn.isEnabled = true
    }

    fun clearButtons() {
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        for (button in createdButtons) {
            buttonContainer?.removeView(button)
        }
        createdButtons.clear()

        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)

        val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
        checkInteractionsBtn.background = checkInteractionsDrawable
        checkInteractionsBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)

        buttonCount = 0
        clearBtn?.isEnabled = false

        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)
        txtAddtwodrugs?.setText(R.string.lbl_add_two_drugs)
        txtAddtwodrugs?.setTypeface(null, Typeface.NORMAL)


        val interactionResultText = requireView().findViewById<TextView>(R.id.txtInteractionResult)
        interactionResultText.visibility = View.GONE


        val drugdrugResults = requireView().findViewById<LinearLayout>(R.id.drugdrugResults)
        drugdrugResults.visibility = View.GONE
    }

    fun checkInteractions() {
//        val resultsFragment = fragment_interaction_result()
        val createdButtons = createdButtons.toList()
        val interactionResultText = requireView().findViewById<TextView>(R.id.txtInteractionResult)
        val drugdrugResults = requireView().findViewById<LinearLayout>(R.id.drugdrugResults)

        if (createdButtons.size == 2 && interactionResultText != null) {
            val drug1 = createdButtons[0].text.toString()
            val drug2 = createdButtons[1].text.toString()

            //         Display the result or handle it accordingly
            val resultMessage = if (true) {
                "Interaction detected between $drug1 and $drug2"
            } else {
                "No interaction detected between $drug1 and $drug2"
            }
//            interactionResultText.text = resultMessage


            val drugOne = requireView().findViewById<TextView>(R.id.drugOne)
            val drugTwo = requireView().findViewById<TextView>(R.id.drugTwo)

            drugOne.text = drug1
            drugTwo.text = drug2
            drugdrugResults.visibility = View.VISIBLE
//            interactionResultText.visibility = View.VISIBLE
        }
    }

    fun interactionResult(drug1: String, drug2: String){

//        id: drugdrugResults








    }

    // Replace this with your actual logic for checking drug interactions
    private fun checkDrugInteractions(drug1: String, drug2: String): Boolean {
        // TODO: Implement your specific logic for drug interactions
        // For now, just return a random result for demonstration purposes
        return (Math.random() < 0.5)
    }



    /////////////////// END OF MY FUNCTIONS =================
    companion object {
        @JvmStatic
        fun newInstance() =
            DrugdrugFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}