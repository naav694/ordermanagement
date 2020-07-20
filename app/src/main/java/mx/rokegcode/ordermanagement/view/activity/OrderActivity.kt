package mx.rokegcode.ordermanagement.view.activity

import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.viewmodel.OrderStateEvent
import mx.rokegcode.ordermanagement.viewmodel.OrderViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class OrderActivity : BaseActivity() {

    private val orderViewModel: OrderViewModel by viewModel()

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
        TODO("Not yet implemented")
    }

    private fun initCustomerDropdown(customers: List<Customer>) {
        val customerAdapter: ArrayAdapter<Customer> = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            customers
        )
        customerDropdown.inputType = InputType.TYPE_NULL
        customerDropdown.setAdapter(customerAdapter)
    }

    private fun subscribeObserver() {
        TODO("Not yet implemented")
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

}