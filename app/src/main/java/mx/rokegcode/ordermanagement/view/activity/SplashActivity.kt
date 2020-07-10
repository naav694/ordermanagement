package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.view.dialog.SweetDialogs
import mx.rokegcode.ordermanagement.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initObservers()
    }

    private fun initObservers() {
        splashViewModel.onLogin.observe(this, Observer {
            when (it) {
                is GenericResult.LoginActivity -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is GenericResult.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is GenericResult.Error -> {
                    SweetDialogs.sweetError(this, it.error)
                }
            }
        })
    }
}