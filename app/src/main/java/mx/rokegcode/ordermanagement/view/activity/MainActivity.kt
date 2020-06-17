package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Order List"
        initComponents()
    }

    private fun initComponents() {
        btnAddOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
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
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}