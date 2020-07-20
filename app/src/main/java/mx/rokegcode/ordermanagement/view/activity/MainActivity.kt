package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.view.adapter.OrderAdapter
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private var orderAdapter: OrderAdapter? = null
    private var mProgressDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Order List"
        initComponent()
        subscribeObserver()
        mainViewModel.getOrders()
    }

    private fun subscribeObserver() {
        mainViewModel.resultOrders.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismiss()
                    initOrderRecyclerView(it.data)
                }
                is DataState.Error -> {
                    mProgressDialog!!.dismiss()
                    SweetDialogs.sweetError(this, "Error: ${it.error.message}").show()
                }
            }
        })
    }

    private fun initComponent() {
        btnAddOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    private fun initOrderRecyclerView(orderList: List<Order>) {
        orderAdapter = OrderAdapter(orderList)
        val layout = LinearLayoutManager(this)
        orderRecyclerView.apply {
            adapter = orderAdapter
            layoutManager = layout
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnLogOut -> {
                sessionHelper.deleteUserSession()
                sessionHelper.setRememberSession(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}