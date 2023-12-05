import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.codingwithme.meowbottomnavigationbar.CalendarFragment
import com.codingwithme.meowbottomnavigationbar.R
import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.icu.text.SimpleDateFormat
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingwithme.meowbottomnavigationbar.MainActivity
import com.codingwithme.meowbottomnavigationbar.RecyclerViewAdapter
import com.codingwithme.meowbottomnavigationbar.RecyclerViewList
import com.codingwithme.meowbottomnavigationbar.SpacesItemDecoration
import com.codingwithme.meowbottomnavigationbar.TimeAdapter
import com.codingwithme.meowbottomnavigationbar.TimeItem
import java.util.*

class ReminderFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var medicineName: EditText
    private var selectedImageResId: Int = R.drawable.img_user_white_a700
    private var selectedImageView: ImageView? = null
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var timeList: ArrayList<TimeItem>
    private lateinit var recyclerViewList: ArrayList<RecyclerViewList> // Declare recyclerViewList
    private lateinit var timeAdapter: TimeAdapter
    private var selectedNumber: String = ""
    private var selectedDuration: String = ""
    private var selectedDosage: String = ""
    private var dosageAmount: String = ""



    private fun saveMedicineName(medicineName: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("medicine_name", medicineName)
            apply()
        }
    }
    private fun saveSelectedTime(selectedTime: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("selected_time", selectedTime)
            apply()
        }
    }
    private fun saveSelectedImage(selectedImageResId: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt("selected_image_res_id", selectedImageResId)
            apply()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val itemToEdit = arguments?.getParcelable<RecyclerViewList>("item")

        // Spinner One
        val spinnerOne: Spinner = view.findViewById(R.id.spinnerDurationOne)
        val numbers = Array(31) { i -> if (i == 0) "freq." else (i).toString() } // Add "No." as the first item
        val adapterOne = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numbers)
        spinnerOne.adapter = adapterOne

        spinnerOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedNumber = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
        }

        // Spinner Two
        val spinnerTwo: Spinner = view.findViewById(R.id.spinnerDurationTwo)
        val durations = arrayOf("Day", "Week", "Month", "Year") // Add "Day" as the first item
        val adapterTwo = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, durations)
        spinnerTwo.adapter = adapterTwo

        spinnerTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedDuration = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
        }

        // Spinner Three
        val spinnerThree: Spinner = view.findViewById(R.id.spinnerDosageOne)
        val dosage = arrayOf("mg", "ml")
        val adapterThree = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dosage)
        spinnerThree.adapter = adapterThree

        spinnerThree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedDosage = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected
            }
        }

        val editText: EditText = view.findViewById(R.id.numberDosage)
        dosageAmount = editText.text.toString()

        val recyclerViewList = ArrayList<RecyclerViewList>()
        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, requireContext(), this)

        // Set up the RecyclerView
        timeList = ArrayList<TimeItem>()
        timeAdapter = TimeAdapter(timeList)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTime)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = timeAdapter
        recyclerView.addItemDecoration(SpacesItemDecoration(10))

        timeAdapter.listener = object : TimeAdapter.OnItemClickListener {
            override fun onEditClick(position: Int) {
                // Handle edit click here
                // You can show a dialog to edit the time
                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        // Format the selected time
                        val calendar = Calendar.getInstance()
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                        val selectedTime = format.format(calendar.time)

                        // Update the time in the list and notify the adapter
                        timeList[position] = TimeItem(selectedTime)
                        timeAdapter.notifyItemChanged(position)
                    },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    false
                )
                timePickerDialog.show()
            }

            override fun onDeleteClick(position: Int) {
                // Handle delete click here
                timeList.removeAt(position)
                timeAdapter.notifyItemRemoved(position)
            }
        }

        //Medicine Time
        val imagePlus: ImageView = view.findViewById(R.id.imagePlus)
        imagePlus.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context,
                { _, selectedHour, selectedMinute ->
                    val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                    Toast.makeText(context, "Selected time: $selectedTime", Toast.LENGTH_SHORT).show()

                    // Format the selected time
                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                    val formattedTime = format.format(calendar.time)

                    // Create a new item with the selected time
                    val newItem = TimeItem(formattedTime)

                    // Add the new item to the list and notify the adapter
                    timeList.add(newItem)
                    timeAdapter.notifyDataSetChanged()
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }


        // Medicine Image
        fun applyBorder(view: ImageView) {
            // Remove border from previously selected image
            selectedImageView?.background = null

            // Create border
            val border = ShapeDrawable(RectShape())
            border.paint.color = Color.GREEN
            border.paint.style = Paint.Style.STROKE
            border.paint.strokeWidth = 10f

            // Create a LayerDrawable with the image and border
            val layers = arrayOf(view.drawable, border)
            val layerDrawable = LayerDrawable(layers)

            // Set the LayerDrawable as the ImageView's drawable
            view.setImageDrawable(layerDrawable)

            // Update selectedImageView
            selectedImageView = view
        }

        val imageCapsuleImage: ImageView = view.findViewById(R.id.imageCapsuleImage)
        imageCapsuleImage.setOnClickListener {
            selectedImageResId = R.drawable.img_image16
            applyBorder(it as ImageView)
        }
        val imageTabletImage: ImageView = view.findViewById(R.id.imageTabletImage)
        imageTabletImage.setOnClickListener {
            selectedImageResId = R.drawable.img_tabletimage
            applyBorder(it as ImageView)
        }
        val imageGelImage: ImageView = view.findViewById(R.id.imageGelImage)
        imageGelImage.setOnClickListener {
            selectedImageResId = R.drawable.img_gelimage
            applyBorder(it as ImageView)
        }
        val imageSyringeImage: ImageView = view.findViewById(R.id.imageSyringeImage)
        imageSyringeImage.setOnClickListener {
            selectedImageResId = R.drawable.img_syringeimage
            applyBorder(it as ImageView)
        }
        val imageSyringeImageOne: ImageView = view.findViewById(R.id.imageSyringeImageOne)
        imageSyringeImageOne.setOnClickListener {
            selectedImageResId = R.drawable.img_syringeimage_63x63
            applyBorder(it as ImageView)
        }
        val imageUser: ImageView = view.findViewById(R.id.imageUser)
        imageUser.setOnClickListener {
            selectedImageResId = R.drawable.img_user_white_a700
            applyBorder(it as ImageView)
        }

        //Medicine Name
        medicineName = view.findViewById(R.id.medicineName)

        val imageArrowLeft: ImageView = view.findViewById(R.id.imageArrowleft)
        imageArrowLeft.setOnClickListener {
            val calendarFragment = CalendarFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, calendarFragment)
                .addToBackStack(null)
                .commit()

            val medicineNameText = medicineName.text.toString()
            saveMedicineName(medicineNameText)
        }

        btnSave.setOnClickListener {
            val medicineNameText = medicineName.text.toString()
            saveMedicineName(medicineNameText)
            saveSelectedImage(selectedImageResId)

            val selectedTimes = timeList.map { it.time }
            saveSelectedTime(selectedTimes.joinToString(","))

            // Get the dosage amount from the EditText
            val editText: EditText = view.findViewById(R.id.numberDosage)
            val dosageAmount = editText.text.toString()

            // Save the user's selections from the spinners and the EditText
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener
            with(sharedPref.edit()) {
                putString("selected_number", selectedNumber)
                putString("selected_duration", selectedDuration)
                putString("selected_dosage", selectedDosage)
                putString("dosage_amount", dosageAmount)
                apply()
            }

            // Create a new RecyclerViewList item for each selected time
            selectedTimes.forEach { selectedTime ->
                val reminderTitle = "$medicineNameText, $dosageAmount $selectedDosage"
                val reminderSubTitle = "$selectedNumber $selectedDuration"
                val newReminder = RecyclerViewList(
                    selectedImageResId,
                    reminderTitle,
                    reminderSubTitle,
                    listOf(selectedTime),
                    selectedNumber,
                    selectedDuration,
                    selectedDosage,
                    dosageAmount
                )
                (activity as MainActivity).addReminder(newReminder)
            }

            Toast.makeText(context, "Reminder Saved!", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance(item: RecyclerViewList? = null) = ReminderFragment().apply {
            arguments = Bundle().apply {
                putParcelable("item", item)
            }
        }
    }

    override fun onItemClick(position: Int) {
        // Get the item to be edited
        val itemToEdit = recyclerViewList[position]

        // Navigate to the edit screen with the details of itemToEdit
        // You might need to create a new Fragment or Activity for editing items
        // Pass itemToEdit to the edit screen so that the user can see the current details and edit them
    }

    override fun onDeleteClick(position: Int) {
        recyclerViewList.removeAt(position)
        recyclerViewAdapter.notifyItemRemoved(position)
    }
}