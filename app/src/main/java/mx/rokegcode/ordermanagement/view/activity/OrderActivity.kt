package mx.rokegcode.ordermanagement.view.activity

import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.view.dialog.AddCustomerDialog
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.view.state.OrderStateEvent
import mx.rokegcode.ordermanagement.viewmodel.OrderViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class OrderActivity : BaseActivity(), AddCustomerDialog.Interactor {

    private val orderViewModel: OrderViewModel by viewModel()
    private var mProgressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Order capture"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        subscribeObserver()
        initComponent()
        orderViewModel.setStateEvent(OrderStateEvent.GetCustomers)
    }

    private fun initComponent() {
        btnAddCustomer.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val addCustomerDialog = AddCustomerDialog.newInstance()
            addCustomerDialog.show(fragmentManager, "add_customer")
        }
        customerDropdown.inputType = InputType.TYPE_NULL
    }

    private fun initCustomerDropdown(customers: List<Customer>) {
        val customerAdapter: ArrayAdapter<Customer> = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            customers
        )
        customerDropdown.setAdapter(customerAdapter)
    }

    private fun subscribeObserver() {
        orderViewModel.customerList.observe(this, Observer {
            when(it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismissWithAnimation()
                    initCustomerDropdown(it.data)
                }
                is DataState.Error -> {
                    SweetDialogs.sweetError(this, it.error.message).show()
                }
            }
        })

        orderViewModel.customerInsert.observe(this, Observer {
            when(it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismissWithAnimation()
                    initCustomerDropdown(it.data)
                }
                is DataState.Error -> {
                    SweetDialogs.sweetError(this, it.error.message).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onAddClicked(customer: Customer) {
        orderViewModel.setStateEvent(OrderStateEvent.SetCustomer(customer))
    }

}