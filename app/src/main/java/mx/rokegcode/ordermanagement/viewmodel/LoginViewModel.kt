package mx.rokegcode.ordermanagement.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository


class LoginViewModel(
    private val userRepository: IUserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var userName: String = ""
    var userPassword: String = ""
    var rememberMe: Boolean = false

    private val _user = MutableLiveData<GenericResult<User>>()
    val user: LiveData<GenericResult<User>> = _user

    init {
        val rememberedUser = sharedPreferences.getString("userName", "")
        val rememberedPass = sharedPreferences.getString("userPassword", "")
        if (!rememberedUser.isNullOrEmpty() && !rememberedPass.isNullOrEmpty()) {
            onLoginFlow(rememberedUser, rememberedPass)
        }
    }

    fun onLogin() {
        onLoginFlow(userName, userPassword)
    }

    private fun onLoginFlow(user: String, password: String) {
        viewModelScope.launch {
            userRepository.onLogin(
                user,
                password
            ).collect {
                if (rememberMe) {
                    when (it) {
                        is GenericResult.Success -> {
                            with(sharedPreferences.edit()) {
                                putString("userName", user)
                                putString("userPassword", password)
                                commit()
                            }
                        }
                    }
                }
                _user.value = it
            }
        }
    }

}