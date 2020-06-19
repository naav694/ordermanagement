package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.databinding.ActivityLoginBinding
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Order Management"

        loginViewModel.user.observe(this, Observer {
            when (it) {
                GenericResult.Loading -> {
                    Toast.makeText(this, "Log In...", Toast.LENGTH_LONG).show()
                }
                is GenericResult.Success -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", it.data)
                    startActivity(intent)
                    finish()
                }
                GenericResult.Error -> {
                    Toast.makeText(this, "Error: $it", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnRegisterUser -> {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}