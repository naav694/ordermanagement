package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.util.interfaces.ISessionHelper


class LoginViewModel(
    private val userRepository: IUserRepository,
    sessionHelper: ISessionHelper
) : BaseViewModel(sessionHelper) {

    var userName: String = ""
    var userPassword: String = ""
    var rememberMe: Boolean = false

    private val _user = MutableLiveData<DataState<User>>()
    val user: LiveData<DataState<User>> get() = _user

    private val _loginForm = MutableLiveData<Boolean>()
    val loginForm: LiveData<Boolean> get() = _loginForm

    fun onLogin() {
        if (isLoginValid()) {
            viewModelScope.launch {
                userRepository.onLogin(
                    userName,
                    userPassword
                ).collect {
                    when (it) {
                        is DataState.Success -> {
                            sessionHelper.setUserSession(it.data)
                            sessionHelper.setRememberSession(rememberMe)
                        }
                    }
                    _user.value = it
                }
            }
        } else {
            _loginForm.value = false
        }
    }

    private fun isLoginValid(): Boolean {
        return !(userName.isEmpty() || userPassword.isEmpty())
    }

}