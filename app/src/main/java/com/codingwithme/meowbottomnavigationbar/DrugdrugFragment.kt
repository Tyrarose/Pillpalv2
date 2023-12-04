package com.codingwithme.meowbottomnavigationbar

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.Random


class DrugdrugFragment : Fragment() {
    private lateinit var adapter: MyAdapter
    private lateinit var items: List<Item>
    private var buttonCount = 0
    private val createdButtons = mutableListOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drugdrug, container, false)
        val listView = view.findViewById<ListView>(R.id.listView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)


        items = listOf(
            Item("Warfarin"), Item("Acetaminophen [Tylenol]"),Item("Abacavir"), Item("Abciximab"),Item("Calcium acetate"), Item("Cobicistat"),Item("Dolutegravir"), Item("Deferasirox"),Item("Lamivudine"), Item("Mannitol"),Item("Dexketoprofen"), Item("Febuxostat"), Item("Procaine benzylpenicillin"), Item("Lorpiprazole"),
            Item("Azelastine"), Item("Tiotropium"), Item("Metoclopramide"), Item("Nepafenac"), Item("Imipenem"),Item("Cloxacillin"), Item("Etravirine"), Item("Ciprofibrate"), Item("Valganciclovir"),Item("Cholic Acid"), Item("Methotrimeprazine"), Item("Pimozide"), Item("Gimeracil"), Item("Kanamycin"),
            Item("Lobeglitazone"), Item("Piretanide"), Item("Hydralazine"), Item("Brinzolamide"), Item("Mianserin"),Item("Ephedrine"), Item("Ceftriaxone"), Item("Lisdexamfetamine"), Item("Desvenlafaxine"),Item("Rasagiline"), Item("Fosamprenavir"), Item("Acenocoumarol"), Item("Isocarboxazid"), Item("Ergonovine"),
            Item("Nitrazepam"), Item("Chlortetracycline"), Item("Drospirenone"), Item("Raltegravir"),Item("Bismuth Subcitrate"), Item("Lopinavir"), Item("Fingolimod"), Item("Magnesium hydroxide"),Item("Pargyline"), Item("Paraldehyde"), Item("Nitrous acid"), Item("Lincomycin"),Item("Dabigatran etexilate"), Item("Estradiol valerate"),
            Item("Riociguat"), Item("Maraviroc"),Item("Citric Acid"), Item("Cabazitaxel"), Item("Ixabepilone"), Item("Lutetium Lu 177 dotatate"),Item("Tibolone"), Item("Tafenoquine"), Item("Mephenytoin"), Item("Lomitapide"),
            Item("Dienogest"), Item("Troleandomycin"), Item("Apixaban"), Item("Artemether"),Item("Fluindione"), Item("Abemaciclib"), Item("Gestodene"), Item("Azidocillin"),Item("Lumefantrine"), Item("Naphazoline"), Item("Bopindolol"), Item("Hydroxyamphetamine"),Item("Iron saccharate"), Item("Magaldrate"), Item("Naloxegol"),
            Item("Pomalidomide"),Item("Ledipasvir"), Item("Methyl salicylate"), Item("Rutin"), Item("Cobicistat"),Item("Cyamemazine"), Item("Apalutamide"), Item("Butylscopolamine"), Item("Eliglustat"),
            Item("Edoxaban"), Item("Lumacaftor"), Item("Potassium Citrate"), Item("Hydrotalcite"),Item("Fenofibric acid"), Item("Olodaterol"), Item("Tixocortol"), Item("Etizolam"),Item("Rucaparib"), Item("Dosulepin"), Item("Pipamperone"), Item("Protocatechualdehyde"),Item("Pramlintide"), Item("Thiosulfuric acid"), Item("Loxoprofen"),
            Item("Trolamine salicylate"),Item("Curcumin"), Item("Amitriptylinoxide"), Item("Normethadone"), Item("Loracarbef"),Item("Enalaprilat"), Item("Dipotassium phosphate"), Item("Propacetamol"), Item("DL-Methylephedrine"),
            Item("Rapacuronium"), Item("Mifamurtide"), Item("Sodium glycerophosphate"), Item("Cyclopenthiazide"),Item("Phenoxyethanol"), Item("Acetyl sulfisoxazole"), Item("Brigatinib"), Item("Testosterone cypionate"),Item("Propiverine"), Item("Piritramide"), Item("Bufexamac"), Item("Artemotil"),Item("Fluticasone"), Item("Nordazepam")
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

        checkInteractionsBtn.isEnabled = false
        clearBtn.isEnabled = false

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItemName = adapter?.getItem(position)?.name
            if (!selectedItemName.isNullOrBlank() && buttonCount < 2) {
                createButton(selectedItemName)
                searchView.setQuery("", false)
                clearBtn.isEnabled = true
            }
        }


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

        checkInteractionsBtn?.setOnClickListener {
            checkInteractions()
        }

        return view
    } /////////////////// END OF ONCREATE =================


    /////////////////// START OF MY FUNCTIONS =================

    private fun createButton(buttonText: String) {
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)


        if (buttonCount < 2) {
            val newButton = Button(requireContext())
            newButton.text = buttonText

            val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_drugdrug, buttonContainer, false)
            val txtItemDrug = itemView.findViewById<TextView>(R.id.txtItemDrug)
            txtItemDrug.text = buttonText

            buttonContainer.addView(itemView)
            createdButtons.add(newButton)
            buttonCount++

            itemView.setOnClickListener {
                removeButton(itemView)
            }
        }

        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)

        if (buttonCount == 2) {
            txtAddtwodrugs?.setText(R.string.lbl_added_two_drugs)
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


    private fun removeButton(view: View) {
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)

        buttonContainer?.removeView(view)
        createdButtons.remove(view)
        buttonCount--

        txtAddtwodrugs?.setText(R.string.lbl_add_two_drugs)
        txtAddtwodrugs?.setTypeface(null, Typeface.NORMAL)


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
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)


        buttonCount = 2
        if (buttonCount == 2) {
            txtAddtwodrugs?.text = getString(R.string.lbl_added_two_drugs)
            txtAddtwodrugs?.setTypeface(null, Typeface.BOLD)

            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.whitetext)
        }


        clearButtons()
        val random = Random()
        for (i in 0 until 2) {
            val randomIndex = random.nextInt(items.size)
            createButton(items[randomIndex].name)
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

        buttonContainer.removeAllViews()
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


//        val interactionResultText = requireView().findViewById<TextView>(R.id.txtInteractionResult)
//        interactionResultText.visibility = View.GONE
//        val drugdrugResults = requireView().findViewById<LinearLayout>(R.id.drugdrugResults)
//        drugdrugResults.visibility = View.GONE
    }

    fun checkInteractions() {
        val createdButtons = createdButtons.toList()
        if (createdButtons.size == 2) {
            val drug1 = createdButtons[0].text.toString()
            val drug2 = createdButtons[1].text.toString()

//            val drugOne = requireView().findViewById<TextView>(R.id.drugOne)
//            val drugTwo = requireView().findViewById<TextView>(R.id.drugTwo)

//            drugOne.text = drug1
//            drugTwo.text = drug2

            // Create an instance of InteractionResultFragment with drug names
            val fragment = InteractionResultFragment.newInstance(drug1, drug2)

            // Navigate to InteractionResultFragment
            val fragmentManager = parentFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_interaction_result, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    fun interactionResult(drug1: String, drug2: String){
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