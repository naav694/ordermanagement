package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.IUserRepository

class RegisterViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val _idUser = MutableLiveData<Long>()
    val idUser: LiveData<Long> = _idUser

    fun onCreateUser(user: User) {
        _idUser.value = userRepository.onSignin(user)
    }

}