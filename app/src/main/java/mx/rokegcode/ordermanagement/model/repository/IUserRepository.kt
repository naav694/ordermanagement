package mx.rokegcode.ordermanagement.model.repository

import mx.rokegcode.ordermanagement.model.data.User

interface IUserRepository {
    fun onLogin(userName: String, userPassword: String): User
    fun onSignin(user: User): Long
}