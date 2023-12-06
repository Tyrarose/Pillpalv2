package com.codingwithme.meowbottomnavigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.io.BufferedReader
import java.io.InputStreamReader
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.opencsv.CSVReader
import java.io.FileReader
import java.io.IOException
import java.io.InputStream
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Paths
import java.nio.file.Files
import java.util.Random

class InteractionResultFragment : Fragment() {

    private lateinit var drug1: String
    private lateinit var drug2: String
    private lateinit var btnSeverity: TextView
    private lateinit var txtDescription: TextView
    private lateinit var txtExtDescription: TextView
    private lateinit var highlight: LinearLayout


    private lateinit var Descriptions: List<deets>

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


        val Descriptions = listOf(
            deets("Ototoxic Activities", "Major", "High risk of ototoxicity", "This drug significantly increases the risk of ototoxicity, potentially leading to hearing loss or other auditory issues. Regular hearing assessments are crucial."),
            deets("Risk or Severity of QTc Prolongation and Torsade de Pointes", "Major", "High risk of cardiac arrhythmias", "This drug interaction poses a major risk of prolonging the QTc interval, leading to the development of torsade de pointes, a potentially life-threatening ventricular arrhythmia. Immediate medical attention is essential to manage the cardiac effects and prevent serious complications."),
            deets("Teratogenic Activities", "Major", "High risk of birth defects", "This drug has teratogenic activities, presenting a major risk of causing birth defects if used during pregnancy. It is crucial to avoid this medication during pregnancy and implement effective contraception to prevent potential harm to the developing fetus."),
            deets("Risk or Severity of Renal Failure and Rhabdomyolysis", "Major", "Significant risk of kidney damage", "This drug interaction significantly increases the risk of renal failure and rhabdomyolysis. Close monitoring of kidney function and prompt intervention are crucial to prevent irreversible damage."),
            deets("Risk or Severity of Pulmonary Toxicity", "Major", "High risk of lung damage", "The drug interaction poses a major risk of pulmonary toxicity, potentially leading to severe lung damage. Close monitoring of respiratory function and prompt medical attention are essential to manage and mitigate these risks."),
            deets("Antineoplastic Activities", "Major", "Potent anticancer effects", "This drug interaction exhibits antineoplastic activities, indicating potent anticancer effects. It is likely used in cancer treatment to inhibit the growth and spread of cancer cells. Close monitoring and management of side effects are essential during cancer therapy."),
            deets("Hypotensive Activities of At", "Major", "Strong blood pressure-lowering effects", "The drug interaction has hypotensive activities, resulting in a major reduction in blood pressure. Monitoring for hypotension-related symptoms and adjusting medication doses are necessary to prevent complications."),
            deets("Central Nervous System Depressant (CNS Depressant) and Hypertensive Activities", "Major", "Combined effects on CNS and blood pressure", "This drug interaction acts as both a central nervous system depressant (CNS depressant) and a hypertensive agent. It can lead to a complex interplay of effects on the nervous system and blood pressure regulation. Careful monitoring and management are required to balance these effects."),
            deets("Risk or Severity of Intraocular Pressure", "Major", "High risk of increased eye pressure", "The drug interaction significantly increases the risk of intraocular pressure, which can lead to glaucoma or exacerbate existing eye conditions. Ophthalmic monitoring and intervention are crucial to prevent vision-related complications."),
            deets("Risk or Severity of Generalized Seizure", "Major", "High risk of generalized seizures", "This drug interaction poses a major risk of inducing generalized seizures. Individuals with a history of seizures or predisposition to seizures require careful monitoring and may need adjustments to their seizure management plan."),
            deets("Risk or Severity of Sedation", "Major", "High risk of excessive sedation", "The drug interaction significantly increases the risk of sedation, potentially leading to excessive drowsiness. Close monitoring of cognitive function and respiratory status is essential, especially in individuals operating machinery or engaging in activities requiring alertness."),
            deets("Serum Concentration", "Minor", "Minor effect on serum concentration", "The drug interaction has a minor impact on the serum concentration of the involved substances. While not a significant concern, monitoring serum levels may be necessary in specific clinical situations."),
            deets("Absorption", "Minor", "Minor impact on drug absorption", "The drug interaction has a minor effect on the absorption of the involved substances. While not typically a significant concern, monitoring absorption may be necessary in specific clinical situations."),
            deets("Risk of Hypersensitivity Reaction to", "Minor", "Risk of hypersensitivity reaction to", "Interaction may increase the risk of hypersensitivity reactions."),
            deets("Analgesic Activities", "Minor", "Minor analgesic effects", "The drug interaction has minor analgesic activities, indicating a slight impact on pain relief. While not typically a significant concern, monitoring for pain management may be necessary in specific clinical situations."),
            deets("Constipating Activities", "Minor", "Potential for mild constipation", "This drug may have a minor impact on bowel movements, leading to a slight increase in constipation."),
            deets("Excretion Rate", "Minor", "Minimal influence on drug excretion", "The drug has a minor effect on the rate at which it is eliminated from the body."),
            deets("Antipsychotic Activities", "Minor", "Mild antipsychotic effects", "The drug exhibits minor antipsychotic properties, which may have a subtle impact on mental and emotional states."),
            deets("Protein Binding", "Minor", "Minimal impact on protein binding", "The drug has a minor effect on protein binding in the bloodstream, with limited potential for interactions with other protein-bound substances."),
            deets("Hyperglycemic Activities", "Minor", "Mild impact on blood glucose levels", "The drug has a minor effect on blood glucose levels, with a slight potential for causing mild hyperglycemia. Regular monitoring of blood sugar is advisable."),
            deets("Dermatologic Adverse Activities", "Minor", "Mild dermatologic side effects", "The drug may cause minor adverse effects on the skin or related structures, such as mild rashes or irritation."),
            deets("Serum Concentration of Sulfate", "Minor", "Minor impact on sulfate levels", "The drug interaction has a minor impact on the serum concentration of sulfate. While not typically a significant concern, monitoring sulfate levels may be necessary in specific clinical situations."),
            deets("Metabolism", "Moderate", "Moderate impact on drug metabolism", "This drug interaction has a moderate impact on the metabolism of the involved substances, potentially affecting the rate at which the drug is broken down and eliminated from the body. Close monitoring may be required to adjust dosage accordingly."),
            deets("Serum Concentration of Active Metabolites", "Moderate", "Moderate impact on active metabolite levels", "This drug interaction moderately influences the serum concentration of active metabolites, which are crucial for therapeutic effects. Monitoring metabolite levels may be necessary to optimize treatment outcomes."),
            deets("Excretion", "Moderate", "Moderate effect on drug excretion", "The drug interaction has a moderate impact on the excretion of the involved substances. Monitoring renal function and adjusting dosage may be necessary to prevent accumulation and toxicity."),
            deets("Diagnostic Agent", "Moderate", "Diagnostic agent", "Interaction may interfere with the diagnostic agent's activity."),
            deets("Bioavailability", "Moderate", "Bioavailability", "Interaction may affect the bioavailability of the drugs involved."),
            deets("Respiratory Depressant Activities", "Moderate", "Moderate risk of respiratory depression", "This drug poses a moderate risk of suppressing respiratory function, particularly at higher doses. Caution is advised, especially in individuals with respiratory conditions."),
            deets("Ulcerogenic Activities", "Moderate", "Moderate risk of ulcer formation", "This drug has a moderate potential to cause the development of ulcers, particularly in the gastrointestinal tract. Close monitoring and preventative measures are recommended."),
            deets("Diuretic Activities", "Moderate", "Moderate diuretic effects", "The drug exhibits moderate diuretic properties, leading to increased urine production. Patients should stay hydrated, and electrolyte levels need close monitoring."),
            deets("Absorption of Reduced Serum Concentration and Potentially", "Moderate", "Moderate impact on absorption", "The drug moderately affects the absorption of nutrients, potentially leading to reduced serum concentration. This may impact the efficacy of certain substances."),
            deets("Hypocalcemic Activities", "Moderate", "Moderate risk of hypocalcemia", "The drug has a moderate potential to cause hypocalcemia (low calcium levels), which may impact various physiological functions. Regular monitoring of calcium levels is advised."),
            deets("No drug interaction found", "None",
            "This drug combination has limited data available on potential interactions.\n" +
                    "While no confirmed risks have been identified, further investigation is needed. " +
                    "Consult your healthcare provider before taking both medications together.",
            "Talk to your doctor, the pharmacist, or any healthcare professional familiar with both medications.\n" +
                    "They can help navigate the uncharted territory and assess any potential risks based on your specific medical history and existing medications."),
        )





        val highlight = view.findViewById<LinearLayout>(R.id.highlight)
        val btnSeverity = view.findViewById<TextView>(R.id.btnSeverity)
        val txtDescription = view.findViewById<TextView>(R.id.txtDescription)
        val txtExtDescription = view.findViewById<TextView>(R.id.txtExtDescription)



        val random = Random()
        val randomIndex = random.nextInt(Descriptions.size)

        btnSeverity.text = Descriptions[randomIndex].severity
        when (btnSeverity.text) {
            // change highlight Color style
            // change btnSeverity Color style
            "Minor" -> {
                highlight.setBackgroundResource(R.drawable.rectangle_bg_white_border_orange_radius_15)
                btnSeverity.setBackgroundResource(R.drawable.rectangle_bg_orange_300_radius_15)
            }
            "Major" -> {
                highlight.setBackgroundResource(R.drawable.rectangle_bg_white_border_violet_radius_15)
                btnSeverity.setBackgroundResource(R.drawable.rectangle_bg_violet_300_radius_15)
            }
            "Moderate" -> {
                highlight.setBackgroundResource(R.drawable.rectangle_bg_white_border_red_radius_15)
                btnSeverity.setBackgroundResource(R.drawable.rectangle_bg_red_300_radius_15)
            }
            "None" -> {
                highlight.setBackgroundResource(R.drawable.rectangle_bg_white_border_green_radius_15)
                btnSeverity.setBackgroundResource(R.drawable.rectangle_bg_green_300_radius_15)
            }
            else -> {
                highlight.setBackgroundResource(R.drawable.rectangle_bg_white_border_black_radius_15)
                btnSeverity.setBackgroundResource(R.drawable.rectangle_bg_black_300_radius_15)
            }
        }
        txtDescription.text = Descriptions[randomIndex].description
        txtExtDescription.text = Descriptions[randomIndex].extended_description



        val checkNewPairbtn = view.findViewById<ImageButton>(R.id.imageArrowleft)
        checkNewPairbtn.setOnClickListener {
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
        btnSeverity = requireView().findViewById(R.id.btnSeverity)
        txtDescription = requireView().findViewById(R.id.txtDescription)
        txtExtDescription = requireView().findViewById(R.id.txtExtDescription)



        return "Interaction Result Here"
    }
}
