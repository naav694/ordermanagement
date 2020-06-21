package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.viewmodel.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.user.observe(this, Observer {
            if (it != null) {
                Log.e("User founded", it.toString())
                openMainActivity(it)
            } else {
                openLoginActivity()
            }
        })
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun openMainActivity(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}