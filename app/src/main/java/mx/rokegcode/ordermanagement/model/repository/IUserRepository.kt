package mx.rokegcode.ordermanagement.model.repository

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User

interface IUserRepository {
    fun onLogin(userName: String, userPassword: String): Flow<GenericResult<User>>
    fun onSignin(user: User): Flow<GenericResult<Long>>
}