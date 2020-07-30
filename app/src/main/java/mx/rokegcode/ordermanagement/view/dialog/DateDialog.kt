package mx.rokegcode.ordermanagement.view.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DateDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var mInteractor: AddDateInteractor

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val format1 = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
        val format2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = format1.parse("$day/${month + 1}/$year")
        mInteractor.onDateSelected(format2.format(date!!))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mInteractor = context as AddDateInteractor
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement AddDateInteractor")
            )
        }
    }

    interface AddDateInteractor {
        fun onDateSelected(date: String)
    }
}