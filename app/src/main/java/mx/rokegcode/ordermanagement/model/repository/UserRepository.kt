package mx.rokegcode.ordermanagement.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.model.db.UserDao

class UserRepository(private val userDao: UserDao) : IUserRepository {

    override fun onLogin(userName: String, userPassword: String): User {
        return runBlocking(Dispatchers.IO) {
            userDao.getUser(userName, userPassword)
        }
    }

    override fun onSignin(user: User): Long {
        return runBlocking(Dispatchers.IO) {
            userDao.insert(user)
        }
    }
}