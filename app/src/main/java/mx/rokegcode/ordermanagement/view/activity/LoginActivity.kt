package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Order Management"

        loginViewModel.user.observe(this, Observer {
            if (it != null) {
                openMainActivity(it)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        })

        initComponents()
    }

    private fun initComponents() {
        btnLogin.setOnClickListener {
            loginViewModel.onLogin(
                editLoginUser.text.toString(),
                editLoginPassword.text.toString()
            )
        }
    }

    private fun openMainActivity(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finish()
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