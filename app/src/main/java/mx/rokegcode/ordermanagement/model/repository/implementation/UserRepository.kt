package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.response.LoginResult

class UserRepository(private val userDao: UserDao) :
    IUserRepository {

    override fun onLogin(userName: String, userPassword: String): Flow<LoginResult<User>> = flow {
        emit(LoginResult.Loading("Log In..."))
        try {
            emit(LoginResult.Success(userDao.getUser(userName, userPassword)))
        } catch (e: Exception) {
            emit(LoginResult.Error(e.message!!))
        }
    }

    override fun onSignin(user: User): Flow<GenericResult<Long>> = flow {
        emit(GenericResult.Loading("Creating new account..."))
        try {
            emit(GenericResult.Success(userDao.insert(user)))
        } catch (e: Exception) {
            emit(GenericResult.Error(e.message!!))
        }
    }
}