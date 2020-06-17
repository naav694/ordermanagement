package mx.rokegcode.ordermanagement.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.databinding.ActivityRegisterBinding
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.viewmodel.RegisterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(
            this, R.layout.activity_register
        )
        binding.lifecycleOwner = this
        binding.registerViewModel = registerViewModel

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sign Up"
        initComponents()
    }

    private fun initComponents() {
        registerViewModel.idUser.observe(this, Observer {
            finish()
        })

        btnCreateAccount.setOnClickListener {
            registerViewModel.onCreateUser(
                User(
                    editFullName.text.toString(),
                    editUserName.text.toString(),
                    editUserPassword.text.toString(),
                    0
                )
            )
        }
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