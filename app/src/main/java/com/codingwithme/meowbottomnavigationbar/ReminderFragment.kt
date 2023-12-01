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
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
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
    private lateinit var timeAdapter: TimeAdapter

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

        val recyclerViewList = ArrayList<RecyclerViewList>()
        recyclerViewAdapter = RecyclerViewAdapter(recyclerViewList, requireContext(), this)

        // Set up the RecyclerView
        timeList = ArrayList<TimeItem>()
        timeAdapter = TimeAdapter(timeList)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewTime)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = timeAdapter
        recyclerView.addItemDecoration(SpacesItemDecoration(10))


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

            // Create a new RecyclerViewList item for each selected time
            selectedTimes.forEach { selectedTime ->
                val newReminder = RecyclerViewList(selectedImageResId, medicineNameText, listOf(selectedTime))
                (activity as MainActivity).addReminder(newReminder)
            }

            Toast.makeText(context, "Reminder Saved!", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }


        return view
    }
    companion object {
        @JvmStatic
        fun newInstance() = ReminderFragment()
    }

    override fun onItemClick(position: Int) {
        // Handle item click here
    }
}