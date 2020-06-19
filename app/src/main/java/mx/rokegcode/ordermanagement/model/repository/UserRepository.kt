package mx.rokegcode.ordermanagement.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import mx.rokegcode.ordermanagement.model.response.GenericResult
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao
import java.lang.RuntimeException

class UserRepository(private val userDao: UserDao) : IUserRepository {

    override fun onLogin(userName: String, userPassword: String): Flow<GenericResult<User>> = flow {
        emit(GenericResult.Loading)
        emit(GenericResult.Success(userDao.getUser(userName, userPassword)))
    }.flowOn(Dispatchers.IO).catch {
        emit(GenericResult.Error)
    }

    override fun onSignin(user: User): Flow<GenericResult<Long>> = flow {
        emit(GenericResult.Loading)
        emit(GenericResult.Success(userDao.insert(user)))
    }.flowOn(Dispatchers.IO).catch {
        emit(GenericResult.Error)
    }
}