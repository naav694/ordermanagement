package mx.rokegcode.ordermanagement.model.repository.interfaces

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.response.LoginResult

interface IUserRepository {
    fun onLogin(userName: String, userPassword: String): Flow<LoginResult<User>>
    fun onSignin(user: User): Flow<GenericResult<Long>>
}