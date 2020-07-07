package mx.rokegcode.ordermanagement.model.response

sealed class LoginResult<out R> {
    data class Success<out T>(val data: T) : LoginResult<T>()
    data class Error(val error: String) : LoginResult<Nothing>()
    data class Loading(val message: String) : LoginResult<Nothing>()
    object LoginActivity : LoginResult<Nothing>()
}