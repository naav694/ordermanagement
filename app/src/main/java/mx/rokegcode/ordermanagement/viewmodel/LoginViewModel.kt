package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.IUserRepository


class LoginViewModel(private val userRepository: IUserRepository) : ViewModel() {

    var userName: String = ""
    var userPassword: String = ""
    var rememberMe: Boolean = false

    private val _user = MutableLiveData<GenericResult<User>>()
    val user: LiveData<GenericResult<User>> = _user

    fun onLogin() {
        viewModelScope.launch {
            userRepository.onLogin(
                userName,
                userPassword
            ).collect {
                _user.value = it
            }
        }
    }

}