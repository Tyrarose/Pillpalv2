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
            deets("Metabolism", "Moderate", "Moderate impact on drug metabolism", "This drug interaction has a moderate impact on the metabolism of the involved substances, potentially affecting the rate at which the drug is broken down and eliminated from the body. Close monitoring may be required to adjust dosage accordingly."),
            deets("Serum Concentration", "Minor", "Minor effect on serum concentration", "The drug interaction has a minor impact on the serum concentration of the involved substances. While not a significant concern, monitoring serum levels may be necessary in specific clinical situations."),
            deets("Risk or Severity of Bleeding", "Major", "High risk of bleeding complications", "This drug interaction significantly increases the risk of bleeding. Close monitoring of coagulation parameters and prompt intervention are crucial to prevent severe bleeding complications."),
            deets("Anticoagulant Activities", "Major", "Potent anticoagulant effects", "The drug interaction exhibits potent anticoagulant activities, affecting blood clotting mechanisms. Careful monitoring of coagulation parameters and adjustment of anticoagulant therapy are necessary to prevent bleeding or thrombotic events."),
            deets("Risk or Severity of Adverse Effects", "Major", "Significant risk of adverse reactions", "This drug interaction poses a major risk of exacerbating adverse effects associated with the involved substances. Close monitoring for adverse reactions and appropriate management are essential."),
            deets("Therapeutic Efficacy", "Major", "High impact on therapeutic effectiveness", "The drug interaction significantly affects the therapeutic efficacy of the involved substances. Adjustments to dosage or alternative treatment options may be necessary to maintain optimal therapeutic outcomes."),
            deets("QTc-Prolonging Activities", "Major", "High risk of QTc prolongation", "This drug interaction increases the risk of QTc prolongation, a condition associated with cardiac arrhythmias. Close monitoring of electrocardiograms and prompt intervention are essential to prevent serious cardiovascular complications."),
            deets("Cardiotoxic Activities", "Major", "Significant risk of cardiotoxicity", "The drug interaction poses a major risk of causing cardiotoxic effects, potentially leading to heart damage. Regular cardiac monitoring is essential to detect and manage cardiac complications promptly."),
            deets("Serum Concentration of Active Metabolites", "Moderate", "Moderate impact on active metabolite levels", "This drug interaction moderately influences the serum concentration of active metabolites, which are crucial for therapeutic effects. Monitoring metabolite levels may be necessary to optimize treatment outcomes."),
            deets("Excretion", "Moderate", "Moderate effect on drug excretion", "The drug interaction has a moderate impact on the excretion of the involved substances. Monitoring renal function and adjusting dosage may be necessary to prevent accumulation and toxicity."),
            deets("Hyperkalemic Activities", "Major", "High risk of hyperkalemia", "This drug interaction significantly increases the risk of hyperkalemia, a condition characterized by elevated potassium levels. Regular monitoring of electrolytes and appropriate management are essential to prevent complications."),
        deets("Nephrotoxic Activities", "Major", "Significant risk of kidney damage", "The drug interaction poses a major risk of nephrotoxicity, potentially leading to kidney damage. Regular monitoring of renal function and prompt intervention are crucial to prevent irreversible harm."),
        deets("Absorption", "Minor", "Minor impact on drug absorption", "The drug interaction has a minor effect on the absorption of the involved substances. While not typically a significant concern, monitoring absorption may be necessary in specific clinical situations."),
        deets("Risk or Severity of Renal Failure", "Major", "High risk of renal failure", "This drug interaction significantly increases the risk of renal failure. Close monitoring of renal function and prompt intervention are crucial to prevent irreversible kidney damage."),
        deets("Immunosuppressive Activities", "Major", "Potent immunosuppressive effects", "The drug interaction exhibits potent immunosuppressive activities, affecting the immune system. Monitoring for signs of immunosuppression and infection is essential for patient safety."),
        deets("Risk or Severity of Hyperkalemia", "Major", "High risk of severe hyperkalemia", "This drug interaction poses a major risk of severe hyperkalemia, which can lead to serious cardiovascular complications. Regular monitoring of potassium levels and appropriate management are crucial."),
        deets("Excretion Rate of Higher Serum Level", "Major", "Significant impact on excretion rate", "The drug interaction significantly affects the excretion rate, leading to higher serum levels of the involved substances. Monitoring renal function and adjusting dosage are necessary to prevent toxicity."),
        deets("Hepatotoxic Activities", "Major", "Significant risk of liver damage", "The drug interaction poses a major risk of hepatotoxicity, potentially leading to liver damage. Regular monitoring of liver function and prompt intervention are crucial to prevent irreversible harm."),
        deets("Neuromuscular Blocking Activities", "Major", "Potent neuromuscular blocking effects", "This drug interaction exhibits potent neuromuscular blocking activities, affecting muscle function. Monitoring for signs of neuromuscular blockade and respiratory function is essential to prevent complications."),
        deets("Neurotoxic Activities", "Major", "Significant risk of neurotoxicity", "The drug interaction poses a major risk of neurotoxic effects, potentially leading to neurological complications. Regular neurological monitoring is crucial for early detection and intervention."),
        deets("Bradycardic Activities", "Major", "High risk of bradycardia", "This drug interaction significantly increases the risk of bradycardia, a slow heart rate. Regular cardiac monitoring is essential to prevent cardiovascular complications."),
        deets("Atrioventricular Blocking (AV Block) Activities", "Major", "Significant risk of AV block", "The drug interaction poses a major risk of atrioventricular (AV) block, disrupting the normal electrical conduction in the heart. Close cardiac monitoring is essential to prevent serious arrhythmias."),
        deets("Hypoglycemic Activities", "Major", "Potent hypoglycemic effects", "The drug interaction exhibits potent hypoglycemic activities, leading to a significant decrease in blood glucose levels. Monitoring blood sugar levels and adjusting antidiabetic therapy are necessary to prevent hypoglycemia."),
        deets("Risk or Severity of Myelosuppression", "Major", "High risk of bone marrow suppression", "This drug interaction significantly increases the risk of myelosuppression, leading to decreased production of blood cells. Regular blood counts are crucial, and prompt intervention may be necessary."),
        deets("Hypercalcemic Activities", "Major", "High risk of hypercalcemia", "This drug interaction poses a major risk of hypercalcemia, characterized by elevated calcium levels. Monitoring calcium levels and appropriate management are crucial to prevent complications."),
        deets("Arrhythmogenic Activities", "Major", "Significant risk of arrhythmias", "The drug interaction significantly increases the risk of arrhythmias, disrupting the normal rhythm of the heart. Close cardiac monitoring is essential to prevent serious cardiovascular complications."),
        deets("Photosensitizing Activities", "Major", "High risk of photosensitivity reactions", "This drug interaction poses a major risk of photosensitizing activities, increasing sensitivity to sunlight. Patients should be advised to take precautions and avoid prolonged sun exposure."),
        deets("Risk or Severity of QTc Prolongation", "Major", "High risk of QTc prolongation", "This drug interaction significantly increases the risk of QTc prolongation, a condition associated with cardiac arrhythmias. Close monitoring of electrocardiograms and prompt intervention are essential to prevent serious cardiovascular complications."),
        deets("Serotonergic Activities", "Major", "Potent serotonergic effects", "The drug interaction exhibits potent serotonergic activities, affecting serotonin levels in the brain. Monitoring for signs of serotonin syndrome and appropriate management are crucial for patient safety."),
        deets("Thrombogenic Activities", "Major", "Significant risk of thrombosis", "This drug interaction significantly increases the risk of thrombosis, a condition characterized by blood clot formation. Close monitoring and antithrombotic measures may be necessary."),
        deets("Antiplatelet Activities", "Major", "Potent antiplatelet effects", "This drug interaction exhibits potent antiplatelet activities, increasing the risk of bleeding. Monitoring for signs of bleeding and adjustments to antiplatelet therapy may be necessary."),
        deets("Risk or Severity of Myopathy and Rhabdomyolysis", "Major", "High risk of muscle and tissue damage", "This drug interaction significantly increases the risk of myopathy and rhabdomyolysis, potentially leading to severe muscle and tissue damage. Regular monitoring of muscle function and prompt intervention are essential."),
        deets("Myopathic Rhabdomyolysis Activities", "Major", "Potent effects on muscle tissue", "The drug interaction has myopathic rhabdomyolysis activities, indicating potent effects on muscle tissue that may lead to severe damage. Close monitoring and early intervention are crucial."),
        deets("Risk or Severity of Rhabdomyolysis, Myoglobinuria, and Elevated Creatine Kinase (CPK)", "Major", "High risk of severe muscle breakdown", "This drug interaction significantly increases the risk of rhabdomyolysis, myoglobinuria, and elevated creatine kinase (CPK) levels. Close monitoring and prompt intervention are essential to prevent complications."),
        deets("Risk or Severity of Rhabdomyolysis", "Major", "High risk of muscle breakdown", "The drug interaction poses a major risk of rhabdomyolysis, a condition characterized by the breakdown of muscle tissue. Regular monitoring of muscle function and early intervention are essential."),
        deets("Risk or Severity of Myopathy, Rhabdomyolysis, and Myoglobinuria", "Major", "High risk of muscle-related complications", "This drug interaction significantly increases the risk of myopathy, rhabdomyolysis, and myoglobinuria. Regular monitoring of muscle function and prompt intervention are crucial to prevent complications."),
        deets("Risk or Severity", "Not Specified", "-", "-"),
        deets("Central Nervous System Depressant (CNS Depressant) Activities", "Major", "Potent CNS depressant effects", "The drug interaction exhibits potent central nervous system depressant (CNS depressant) activities, leading to sedation and reduced brain activity. Close monitoring of neurological function is essential."),
        deets("Neuroexcitatory Activities", "Major", "Potent neuroexcitatory effects", "This drug interaction has potent neuroexcitatory activities, potentially leading to heightened neuronal activity. Monitoring for signs of overstimulation and appropriate management are crucial."),
        deets("Sedative Activities", "Major", "Strong sedative effects", "The drug interaction exhibits strong sedative activities, leading to significant drowsiness and relaxation. Monitoring for excessive sedation and adjustments to dosage may be necessary."),
        deets("Diagnostic Agent", "Moderate", "Diagnostic agent", "Interaction may interfere with the diagnostic agent's activity."),
        deets("Hyponatremic Activities", "Major", "High risk of hyponatremia", "This drug interaction significantly increases the risk of hyponatremia, a condition characterized by low sodium levels. Regular monitoring of electrolytes is crucial to prevent complications."),
        deets("Antihypertensive Activities", "Major", "Potent antihypertensive effects", "The drug interaction exhibits potent antihypertensive activities, leading to a significant reduction in blood pressure. Monitoring for hypotension-related symptoms and adjusting antihypertensive therapy are necessary."),
        deets("Hypotensive Activities", "Major", "Strong hypotensive effects", "The drug interaction has strong hypotensive activities, resulting in a significant decrease in blood pressure. Monitoring for hypotension-related symptoms and adjusting medication doses are necessary."),
        deets("Orthostatic Hypotensive Activities", "Major", "Significant risk of orthostatic hypotension", "This drug interaction significantly increases the risk of orthostatic hypotension, a drop in blood pressure upon standing. Monitoring for dizziness and adjusting medication doses are necessary."),
        deets("Risk or Severity of Angioedema", "Major", "High risk of angioedema", "The drug interaction poses a major risk of angioedema, a condition characterized by severe swelling, especially in the face and throat. Prompt medical attention is essential to prevent airway obstruction."),
        deets("Risk or Severity of Hypotension, Nitritoid Reactions, Facial Flushing, Nausea, and Vomiting", "Major", "Significant risk of adverse effects", "This drug interaction significantly increases the risk of hypotension, nitritoid reactions, facial flushing, nausea, and vomiting. Monitoring for these adverse effects and appropriate management are crucial."),
        deets("Risk or Severity of Anemia and Severe Leukopenia", "Major", "High risk of blood-related complications", "This drug interaction significantly increases the risk of anemia and severe leukopenia, leading to decreased red blood cells and white blood cells. Regular blood counts are crucial, and prompt intervention may be necessary."),
        deets("Vasodilatory Activities", "Major", "Potent vasodilatory effects", "The drug interaction exhibits potent vasodilatory activities, leading to the widening of blood vessels. Monitoring for changes in blood pressure and appropriate management are necessary."),
        deets("Risk or Severity of Ventricular Arrhythmias", "Major", "High risk of abnormal heart rhythms", "This drug interaction significantly increases the risk of ventricular arrhythmias, disrupting the normal rhythm of the heart. Close cardiac monitoring is essential to prevent serious cardiovascular complications."),
        deets("Risk or Severity of Renal Failure and Hyperkalemia", "Major", "High risk of kidney damage and elevated potassium levels", "This drug interaction significantly increases the risk of renal failure and hyperkalemia. Regular monitoring of renal function and electrolytes is crucial to prevent complications."),
        deets("Adverse Neuromuscular Activities", "Major", "Potent adverse effects on neuromuscular function", "This drug interaction has potent adverse neuromuscular activities, potentially leading to impaired muscle and nerve function. Close monitoring of neuromuscular status is essential."),
        deets("Bioavailability", "Moderate", "Bioavailability", "Interaction may affect the bioavailability of the drugs involved."),
        deets("Hypokalemic Activities", "Major", "High risk of hypokalemia", "This drug interaction significantly increases the risk of hypokalemia, a condition characterized by low potassium levels. Regular monitoring of electrolytes is crucial to prevent complications."),
        deets("Fluid Retaining Activities", "Major", "Significant fluid retention effects", "The drug interaction has fluid retaining activities, leading to increased fluid accumulation. Monitoring for signs of edema and managing fluid balance are necessary to prevent complications."),
        deets("Hypertensive Activities", "Major", "Potent hypertensive effects", "The drug interaction exhibits potent hypertensive activities, resulting in a significant increase in blood pressure. Monitoring for hypertensive-related symptoms and adjusting medication doses are necessary."),
        deets("Hypotensive and Central Nervous System Depressant (CNS Depressant) Activities", "Major", "Combined effects on blood pressure and CNS function", "This drug interaction has hypotensive and central nervous system depressant (CNS depressant) activities, affecting both blood pressure and CNS function. Close monitoring and appropriate management are essential."),
        deets("Central Neurotoxic Activities", "Major", "Potent central neurotoxic effects", "The drug interaction exhibits potent central neurotoxic activities, potentially leading to adverse effects on the central nervous system. Monitoring for neurological symptoms and appropriate management are crucial."),
        deets("Anticholinergic Activities", "Major", "Potent anticholinergic effects", "The drug interaction has potent anticholinergic activities, affecting the function of the autonomic nervous system. Monitoring for anticholinergic-related side effects is essential."),
        deets("Risk or Severity of Hypertension", "Major", "High risk of hypertension", "The drug interaction significantly increases the risk of hypertension, potentially leading to serious cardiovascular complications. Monitoring for hypertensive-related symptoms and adjusting medication doses are necessary."),
        deets("Risk or Severity of Convulsion", "Major", "High risk of seizures", "This drug interaction significantly increases the risk of convulsions. Individuals with a history of seizures or predisposition to seizures require careful monitoring and may need adjustments to their seizure management plan."),
        deets("Risk or Severity of Serotonin Syndrome", "Major", "High risk of serotonin syndrome", "The drug interaction significantly increases the risk of serotonin syndrome, a potentially life-threatening condition. Monitoring for signs of serotonin toxicity and appropriate management are crucial."),
        deets("Stimulatory Activities", "Major", "Potent stimulatory effects", "The drug interaction exhibits potent stimulatory activities, potentially leading to increased nervous system activity. Monitoring for signs of overstimulation and appropriate management are crucial."),
        deets("Tachycardic Activities", "Major", "Significant risk of rapid heart rate", "The drug interaction has tachycardic activities, leading to a significant increase in heart rate. Regular cardiac monitoring is essential to prevent cardiovascular complications."),
        deets("Risk or Severity of Sedation and Somnolence", "Major", "High risk of excessive sedation and drowsiness", "This drug interaction significantly increases the risk of sedation and somnolence, potentially leading to excessive drowsiness. Close monitoring of cognitive function and respiratory status is essential."),
        deets("Bronchoconstrictory Activities", "Major", "Potent bronchoconstrictive effects", "The drug interaction exhibits potent bronchoconstrictory activities, potentially leading to airway constriction. Monitoring for respiratory symptoms and appropriate management are crucial."),
        deets("Risk or Severity of Congestive Heart Failure and Hypotension", "Major", "Significant risk of heart failure and low blood pressure", "This drug interaction significantly increases the risk of congestive heart failure and hypotension. Close monitoring of cardiac function and blood pressure is essential to prevent complications."),
        deets("Vasoconstricting Activities", "Major", "Potent vasoconstrictive effects", "The drug interaction exhibits potent vasoconstricting activities, leading to the narrowing of blood vessels. Monitoring for changes in blood pressure and appropriate management are necessary."),
        deets("Bronchodilatory Activities", "Major", "Potent bronchodilatory effects", "The drug interaction has potent bronchodilatory activities, leading to the widening of airways. Monitoring for respiratory symptoms and appropriate management are necessary."),
        deets("Analgesic Activities", "Minor", "Minor analgesic effects", "The drug interaction has minor analgesic activities, indicating a slight impact on pain relief. While not typically a significant concern, monitoring for pain management may be necessary in specific clinical situations."),
        deets("Constipating Activities", "Minor", "Potential for mild constipation", "This drug may have a minor impact on bowel movements, leading to a slight increase in constipation."),
        deets("Excretion Rate", "Minor", "Minimal influence on drug excretion", "The drug has a minor effect on the rate at which it is eliminated from the body."),
        deets("Respiratory Depressant Activities", "Moderate", "Moderate risk of respiratory depression", "This drug poses a moderate risk of suppressing respiratory function, particularly at higher doses. Caution is advised, especially in individuals with respiratory conditions."),
        deets("Risk or Severity of QTc Prolongation, Torsade de Pointes, Hypokalemia, Hypomagnesemia, and Cardiac Arrest", "Major", "Critical risk of severe cardiac events", "There is a major risk of life-threatening cardiac events, including QTc prolongation, torsade de pointes, hypokalemia, hypomagnesemia, and cardiac arrest. Immediate medical attention is crucial."),
        deets("Antipsychotic Activities", "Minor", "Mild antipsychotic effects", "The drug exhibits minor antipsychotic properties, which may have a subtle impact on mental and emotional states."),
        deets("Ulcerogenic Activities", "Moderate", "Moderate risk of ulcer formation", "This drug has a moderate potential to cause the development of ulcers, particularly in the gastrointestinal tract. Close monitoring and preventative measures are recommended."),
        deets("Hypertensive and Vasoconstricting Activities", "Major", "Significant risk of hypertension and vasoconstriction", "The drug significantly increases the risk of elevated blood pressure and vasoconstriction, posing a major concern for individuals with hypertension or cardiovascular issues."),
        deets("Vasopressor Activities", "Major", "Strong vasoconstrictor effects", "This drug acts as a potent vasopressor, leading to significant constriction of blood vessels and increased blood pressure. Caution is essential, especially in patients with cardiovascular conditions."),
        deets("Risk or Severity of Hypokalemia", "Major", "Critical risk of severe hypokalemia", "There is a major risk of experiencing severe hypokalemia (low potassium levels), which can lead to various complications. Monitoring potassium levels is crucial, and supplementation may be required."),
        deets("Risk or Severity of Ototoxicity and Nephrotoxicity", "Major", "High risk of ear and kidney damage", "This drug poses a major risk of causing damage to the ears (ototoxicity) and kidneys (nephrotoxicity). Regular monitoring of auditory and renal function is essential."),
        deets("Diuretic Activities", "Moderate", "Moderate diuretic effects", "The drug exhibits moderate diuretic properties, leading to increased urine production. Patients should stay hydrated, and electrolyte levels need close monitoring."),
        deets("Risk or Severity of Heart Failure", "Major", "Significant risk of exacerbating heart failure", "This drug significantly increases the risk of worsening heart failure symptoms. It should be used with caution or avoided in individuals with heart failure."),
        deets("Protein Binding", "Minor", "Minimal impact on protein binding", "The drug has a minor effect on protein binding in the bloodstream, with limited potential for interactions with other protein-bound substances."),
        deets("Risk or Severity of Edema Formation", "Major", "Significant risk of edema", "This drug poses a major risk of causing fluid retention and edema. Monitoring for signs of swelling and managing fluid balance is crucial."),
        deets("Risk or Severity of Fluid Retention", "Major", "High risk of fluid accumulation", "There is a major risk of significant fluid retention, which can lead to edema and other complications. Close monitoring and management of fluid balance are essential."),
        deets("Hyperglycemic Activities", "Minor", "Mild impact on blood glucose levels", "The drug has a minor effect on blood glucose levels, with a slight potential for causing mild hyperglycemia. Regular monitoring of blood sugar is advisable."),
        deets("Risk or Severity of Severe Leukopenia", "Major", "Critical risk of severe leukopenia", "This drug poses a major risk of causing severe leukopenia (low white blood cell count), increasing susceptibility to infections. Regular blood cell counts are essential."),
        deets("Risk or Severity of Hyponatremia", "Major", "Critical risk of severe hyponatremia", "There is a major risk of experiencing severe hyponatremia (low sodium levels), which can lead to serious neurological complications. Frequent monitoring of sodium levels is crucial."),
        deets("Risk or Severity of Myopathy", "Major", "High risk of muscle damage", "The drug significantly increases the risk of myopathy (muscle damage), which may manifest as weakness or pain. Monitoring of muscle function and creatine kinase levels is essential."),
        deets("Dermatologic Adverse Activities", "Minor", "Mild dermatologic side effects", "The drug may cause minor adverse effects on the skin or related structures, such as mild rashes or irritation."),
        deets("Absorption of Reduced Serum Concentration and Potentially", "Moderate", "Moderate impact on absorption", "The drug moderately affects the absorption of nutrients, potentially leading to reduced serum concentration. This may impact the efficacy of certain substances."),
        deets("Myelosuppressive Activities", "Major", "High risk of bone marrow suppression", "This drug poses a major risk of suppressing bone marrow function, leading to decreased production of blood cells. Regular blood counts are crucial, and prompt intervention may be necessary."),
        deets("Risk or Severity of Hypotension and Neuromuscular Blockade", "Major", "Significant risk of hypotension and neuromuscular blockade", "There is a major risk of experiencing severe hypotension and neuromuscular blockade, which can lead to cardiovascular and respiratory compromise. Monitoring vital signs is essential."),
        deets("Risk or Severity of Sinus Node Depression", "Major", "Significant risk of sinus node depression", "The drug significantly increases the risk of depressing the sinus node, potentially leading to bradycardia and other cardiac issues. Close cardiac monitoring is crucial."),
        deets("Risk or Severity of Generalized Seizure and Bradycardia", "Major", "High risk of generalized seizure and bradycardia", "This drug poses a major risk of causing generalized seizures and bradycardia. Monitoring for neurological and cardiac symptoms is essential."),
        deets("Hypocalcemic Activities", "Moderate", "Moderate risk of hypocalcemia", "The drug has a moderate potential to cause hypocalcemia (low calcium levels), which may impact various physiological functions. Regular monitoring of calcium levels is advised."),
        deets("Thrombocytopenic Activities", "Major", "High risk of thrombocytopenia", "This drug significantly increases the risk of thrombocytopenia (low platelet count), leading to an increased susceptibility to bleeding. Frequent blood counts are essential."),
        deets("Neutropenic Activities", "Major", "High risk of neutropenia", "There is a major risk of experiencing neutropenia (low neutrophil count), increasing susceptibility to bacterial infections. Regular blood counts are crucial."),
        deets("Risk or Severity of Bradycardia", "Major", "Significant risk of bradycardia", "The drug poses a major risk of causing bradycardia (slow heart rate), which can lead to cardiovascular complications. Regular cardiac monitoring is essential."),
        deets("Ototoxic Activities", "Major", "High risk of ototoxicity", "This drug significantly increases the risk of ototoxicity, potentially leading to hearing loss or other auditory issues. Regular hearing assessments are crucial."),
        deets("Risk or Severity of QTc Prolongation and Torsade de Pointes", "Major", "High risk of cardiac arrhythmias", "This drug interaction poses a major risk of prolonging the QTc interval, leading to the development of torsade de pointes, a potentially life-threatening ventricular arrhythmia. Immediate medical attention is essential to manage the cardiac effects and prevent serious complications."),
        deets("Teratogenic Activities", "Major", "High risk of birth defects", "This drug has teratogenic activities, presenting a major risk of causing birth defects if used during pregnancy. It is crucial to avoid this medication during pregnancy and implement effective contraception to prevent potential harm to the developing fetus."),
        deets("Risk or Severity of Renal Failure and Rhabdomyolysis", "Major", "Significant risk of kidney damage", "This drug interaction significantly increases the risk of renal failure and rhabdomyolysis. Close monitoring of kidney function and prompt intervention are crucial to prevent irreversible damage."),
        deets("Risk or Severity of Pulmonary Toxicity", "Major", "High risk of lung damage", "The drug interaction poses a major risk of pulmonary toxicity, potentially leading to severe lung damage. Close monitoring of respiratory function and prompt medical attention are essential to manage and mitigate these risks."),
        deets("Antineoplastic Activities", "Major", "Potent anticancer effects", "This drug interaction exhibits antineoplastic activities, indicating potent anticancer effects. It is likely used in cancer treatment to inhibit the growth and spread of cancer cells. Close monitoring and management of side effects are essential during cancer therapy."),
        deets("Hypotensive Activities of At", "Major", "Strong blood pressure-lowering effects", "The drug interaction has hypotensive activities, resulting in a major reduction in blood pressure. Monitoring for hypotension-related symptoms and adjusting medication doses are necessary to prevent complications."),
        deets("Central Nervous System Depressant (CNS Depressant) and Hypertensive Activities", "Major", "Combined effects on CNS and blood pressure", "This drug interaction acts as both a central nervous system depressant (CNS depressant) and a hypertensive agent. It can lead to a complex interplay of effects on the nervous system and blood pressure regulation. Careful monitoring and management are required to balance these effects."),
        deets("Risk or Severity of Intraocular Pressure", "Major", "High risk of increased eye pressure", "The drug interaction significantly increases the risk of intraocular pressure, which can lead to glaucoma or exacerbate existing eye conditions. Ophthalmic monitoring and intervention are crucial to prevent vision-related complications."),
        deets("Serum Concentration of Sulfate", "Minor", "Minor impact on sulfate levels", "The drug interaction has a minor impact on the serum concentration of sulfate. While not typically a significant concern, monitoring sulfate levels may be necessary in specific clinical situations."),
        deets("Risk or Severity of Generalized Seizure", "Major", "High risk of generalized seizures", "This drug interaction poses a major risk of inducing generalized seizures. Individuals with a history of seizures or predisposition to seizures require careful monitoring and may need adjustments to their seizure management plan."),
        deets("Risk or Severity of Sedation", "Major", "High risk of excessive sedation", "The drug interaction significantly increases the risk of sedation, potentially leading to excessive drowsiness. Close monitoring of cognitive function and respiratory status is essential, especially in individuals operating machinery or engaging in activities requiring alertness."),
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
