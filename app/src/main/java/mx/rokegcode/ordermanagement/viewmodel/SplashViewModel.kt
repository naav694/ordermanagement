package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.IUserRepository

class SplashViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    /*fun onLogin(userName: String, userPassword: String) {
        _user.value = userRepository.onLogin(userName, userPassword)
    }*/

}