package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.response.LoginResult

class UserRepository(private val userDao: UserDao) :
    IUserRepository {

    @ExperimentalCoroutinesApi
    override fun onLogin(userName: String, userPassword: String): Flow<LoginResult<User>> = flow {
        emit(LoginResult.Loading("Log In..."))
        try {
            val response = userDao.getUser(userName, userPassword)
            emit(LoginResult.Success(response))
        } catch (e: Exception) {
            emit(LoginResult.Error(e.message!!))
        }
    }

    @ExperimentalCoroutinesApi
    override fun onSignin(user: User): Flow<GenericResult<Long>> = flow {
        emit(GenericResult.Loading("Creating new account..."))
        emit(GenericResult.Success(userDao.insert(user)))
    }.flowOn(Dispatchers.IO).catch {
        emit(GenericResult.Error(it.message!!))
    }
}