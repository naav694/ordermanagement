package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.databinding.ActivityLoginBinding
import mx.rokegcode.ordermanagement.model.response.LoginResult
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()

    private var mProgressDialog: SweetAlertDialog? = null

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

        initObservers()
        textSignIng.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun initObservers() {
        loginViewModel.user.observe(this, Observer {
            when (it) {
                is LoginResult.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is LoginResult.Success -> {
                    mProgressDialog!!.dismiss()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginResult.Error -> {
                    mProgressDialog!!.dismiss()
                    SweetDialogs.sweetError(this, "Error: ${it.error}").show()
                }
            }
        })
        loginViewModel.loginForm.observe(this, Observer {
            SweetDialogs.sweetWarning(this, "Debe seleccionar una empresa e ingresar usuario y contraseña")
        })
    }

}