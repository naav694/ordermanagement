package mx.rokegcode.ordermanagement.util.interfaces

import mx.rokegcode.ordermanagement.model.data.User

interface ISessionHelper {
    fun getUserSession(): User
    fun setUserSession(user: User)
    fun deleteUserSession()
    fun setRememberSession(remember: Boolean)
    fun getRememberSession(): Boolean
}