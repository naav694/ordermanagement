package mx.rokegcode.ordermanagement.model.repository.interfaces

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.util.DataState

interface IUserRepository {
    fun onLogin(userName: String, userPassword: String): Flow<DataState<User>>
    fun onSignin(user: User): Flow<DataState<Long>>
}