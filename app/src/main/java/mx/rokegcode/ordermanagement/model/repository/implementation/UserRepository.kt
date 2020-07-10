package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.response.GenericResult

class UserRepository(private val userDao: UserDao) :
    IUserRepository {

    override fun onLogin(userName: String, userPassword: String): Flow<GenericResult<User>> = flow {
        emit(GenericResult.Loading("Log In..."))
        try {
            emit(GenericResult.Success(userDao.getUser(userName, userPassword)))
        } catch (e: Exception) {
            emit(GenericResult.Error(e.message!!))
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