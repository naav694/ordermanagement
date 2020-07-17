package mx.rokegcode.ordermanagement.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.util.interfaces.ISessionHelper
import org.koin.android.ext.android.inject

open class BaseActivity : AppCompatActivity() {
    val sessionHelper: ISessionHelper by inject()
    lateinit var userSession: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSession = sessionHelper.getUserSession()
    }
}