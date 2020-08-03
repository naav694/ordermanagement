package mx.rokegcode.ordermanagement.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.view.dialog.AddCustomerDialog
import mx.rokegcode.ordermanagement.view.dialog.DateDialog
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.view.dialog.TimeDialog
import mx.rokegcode.ordermanagement.view.state.OrderStateEvent
import mx.rokegcode.ordermanagement.viewmodel.OrderViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class OrderActivity : BaseActivity(), AddCustomerDialog.Interactor, DateDialog.Interactor,
    TimeDialog.Interactor {

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
            AddCustomerDialog().show(supportFragmentManager, "add_customer")
        }
        editDeliveryDate.setOnClickListener {
            DateDialog().show(supportFragmentManager, "date_picker")
        }
        editDeliveryHour.setOnClickListener {
            TimeDialog().show(supportFragmentManager, "time_picker")
        }
        btnCreateOrder.setOnClickListener {
            val customer: Customer = spinnerCustomer.selectedItem as Customer
            val order = Order(
                customer.idCustomer,
                "",
                editDeliveryDate.text.toString(),
                editDeliveryHour.text.toString(),
                editDeliveryPlace.text.toString(),
                editTypeProduct.text.toString(),
                editFillingProduct.text.toString(),
                editProductDesign.text.toString(),
                editProductPrice.text.toString().toDouble(),
                editAdvanceOrder.text.toString().toDouble(),
                0.0,
                0
            )
            orderViewModel.setStateEvent(OrderStateEvent.SetOrder(order))
        }
    }

    private fun subscribeObserver() {
        orderViewModel.customerList.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismissWithAnimation()
                    initCustomerSpinner(it.data)
                }
                is DataState.Error -> {
                    SweetDialogs.sweetError(this, it.error.message).show()
                }
            }
        })

        orderViewModel.customerInsert.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismissWithAnimation()
                    initCustomerSpinner(it.data)
                }
                is DataState.Error -> {
                    SweetDialogs.sweetError(this, it.error.message).show()
                }
            }
        })

        orderViewModel.orderInsert.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismissWithAnimation()
                    SweetDialogs.sweetSuccessCloseActivity(
                        this,
                        "Order # ${it.data} created successfully!",
                        this
                    ).show()
                }
                is DataState.Error -> {
                    SweetDialogs.sweetError(this, it.error.message).show()
                }
            }
        })
    }

    private fun initCustomerSpinner(customers: List<Customer>) {
        val customerAdapter: ArrayAdapter<Customer> = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            customers
        )
        spinnerCustomer.adapter = customerAdapter
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

    override fun onDateSelected(date: String) {
        editDeliveryDate.setText(date)
    }

    override fun onTimeSelected(time: String) {
        editDeliveryHour.setText(time)
    }

}