package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.response.DataState
import mx.rokegcode.ordermanagement.support.interfaces.ISessionHelper

class SplashViewModel(
    private val userRepository: IUserRepository,
    sessionHelper: ISessionHelper
) : BaseViewModel(sessionHelper) {

    private var _onLogin = MutableLiveData<DataState<User>>()
    val onLogin: LiveData<DataState<User>> get() = _onLogin

    init {
        viewModelScope.launch {
            val remember = sessionHelper.getRememberSession()
            if (!remember) {
                delay(1000)
                _onLogin.value = DataState.LoginActivity
            } else {
                val user = sessionHelper.getUserSession()
                delay(1000)
                userRepository.onLogin(
                    user.userName,
                    user.userPassword
                ).collect {
                    _onLogin.value = it
                }
            }
        }
    }

}