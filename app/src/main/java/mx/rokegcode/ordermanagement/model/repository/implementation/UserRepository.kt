package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.util.DataState

class UserRepository(private val userDao: UserDao) :
    IUserRepository {

    override fun onLogin(userName: String, userPassword: String): Flow<DataState<User>> = flow {
        emit(DataState.Loading("Log In..."))
        delay(1000)
        try {
            emit(DataState.Success(userDao.getUser(userName, userPassword)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun onSignin(user: User): Flow<DataState<Long>> = flow {
        emit(DataState.Loading("Creating new account..."))
        delay(1000)
        try {
            emit(DataState.Success(userDao.insert(user)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}