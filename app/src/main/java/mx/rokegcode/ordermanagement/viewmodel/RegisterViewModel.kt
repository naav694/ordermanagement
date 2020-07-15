package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.response.DataState

class RegisterViewModel(private val userRepository: IUserRepository) : ViewModel() {

    var fullName: String = ""
    var userName: String = ""
    var userPassword: String = ""


    private val _idUser = MutableLiveData<DataState<Long>>()
    val idUser: LiveData<DataState<Long>> = _idUser

    fun onCreateUser() {
        val user = User()
        user.fullName = fullName
        user.userName = userName
        user.userPassword = userPassword
        viewModelScope.launch {
            userRepository.onSignin(user).collect {
                _idUser.value = it
            }
        }
    }

}