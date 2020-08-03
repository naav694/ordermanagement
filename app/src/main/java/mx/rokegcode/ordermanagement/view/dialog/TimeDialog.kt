package mx.rokegcode.ordermanagement.view.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimeDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var mInteractor: Interactor

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mInteractor.onTimeSelected(String.format(Locale.getDefault(), "%d:%d", hourOfDay, minute))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mInteractor = context as Interactor
        } catch (e: ClassCastException) {
            throw ClassCastException(
                ("$context must implement AddDateInteractor")
            )
        }
    }

    interface Interactor {
        fun onTimeSelected(time: String)
    }
}