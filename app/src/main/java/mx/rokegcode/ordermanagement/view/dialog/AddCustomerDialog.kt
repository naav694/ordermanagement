package mx.rokegcode.ordermanagement.view.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_customer_dialog.view.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Customer

class AddCustomerDialog : DialogFragment() {

    private lateinit var mInteractor: Interactor

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.add_customer_dialog, null)
            builder.setView(dialogView)
                .setMessage("Customer Capture")
                .setCancelable(false)
                .setPositiveButton("Add") { dialog, _ ->
                    mInteractor.onAddClicked(
                        Customer(
                            0,
                            dialogView.editCustomerName.text.toString(),
                            dialogView.editCustomerLastName.text.toString(),
                            dialogView.editCustomerPhone.text.toString(),
                            dialogView.editCustomerAddress.text.toString()
                        )
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mInteractor = context as Interactor
        } catch (e: ClassCastException) {
            throw ClassCastException(
                ("$context must implement AddCustomerCallback")
            )
        }
    }

    interface Interactor {
        fun onAddClicked(customer: Customer)
    }

}