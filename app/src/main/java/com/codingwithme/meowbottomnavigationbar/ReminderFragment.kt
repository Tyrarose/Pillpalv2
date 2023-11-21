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
import android.widget.TextView
import com.codingwithme.meowbottomnavigationbar.RecyclerViewAdapter
import java.util.*

class ReminderFragment : Fragment() {

    private lateinit var medicineName: EditText
    private var selectedImageResId: Int = R.drawable.img_image16
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)

        //Medicine Time Edit
        val txtTimeB: TextView = view.findViewById(R.id.txtTimeB)
        txtTimeB.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                context,
                { _, selectedHour, selectedMinute ->
                    val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                    txtTimeB.text = selectedTime
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }

        //Medicine Time
        val imagePlus: ImageView = view.findViewById(R.id.imagePlus)
        imagePlus.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val is24HourFormat = android.text.format.DateFormat.is24HourFormat(context)
            TimePickerDialog(context, { _, selectedHour, selectedMinute ->
            }, hour, minute, is24HourFormat).show()
        }
        //Medicine Image
        val imageCapsuleImage: ImageView = view.findViewById(R.id.imageCapsuleImage)
        imageCapsuleImage.setOnClickListener {
            selectedImageResId = R.drawable.img_image16
        }
        val imageTabletImage: ImageView = view.findViewById(R.id.imageTabletImage)
        imageTabletImage.setOnClickListener {
            selectedImageResId = R.drawable.img_tabletimage
        }
        val imageSyringeImage: ImageView = view.findViewById(R.id.imageSyringeImage)
        imageCapsuleImage.setOnClickListener {
            selectedImageResId = R.drawable.img_syringeimage
        }
        val imageSyringeImageOne: ImageView = view.findViewById(R.id.imageSyringeImageOne)
        imageTabletImage.setOnClickListener {
            selectedImageResId = R.drawable.img_syringeimage_63x63
        }
        val imageGelImage: ImageView = view.findViewById(R.id.imageGelImage)
        imageCapsuleImage.setOnClickListener {
            selectedImageResId = R.drawable.img_gelimage
        }
        val imageUser: ImageView = view.findViewById(R.id.imageUser)
        imageTabletImage.setOnClickListener {
            selectedImageResId = R.drawable.img_user_white_a700
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

            // Save the medicine name when the reminder is set
            val medicineNameText = medicineName.text.toString()
            saveMedicineName(medicineNameText)
        }

        return view
    }

    private fun saveMedicineName(medicineName: String) {
        // Implement your method to save the medicine name
    }

    companion object {
        @JvmStatic
        fun newInstance() = ReminderFragment()
    }
}
