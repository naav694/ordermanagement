package mx.rokegcode.ordermanagement.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.toolbar.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.databinding.ActivityRegisterBinding
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.viewmodel.RegisterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by viewModel()
    private var mProgressDialog: SweetAlertDialog? = null

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

        registerViewModel.idUser.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                    mProgressDialog = SweetDialogs.sweetLoading(this, it.message)
                    mProgressDialog!!.show()
                }
                is DataState.Success -> {
                    mProgressDialog!!.dismiss()
                    if (it.data > 0) {
                        finish()
                    } else {
                        Toast.makeText(this, "Error: Account was not created", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is DataState.Error -> {
                    mProgressDialog!!.dismiss()
                    SweetDialogs.sweetError(this, "Error: ${it.error.message}").show()
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

}