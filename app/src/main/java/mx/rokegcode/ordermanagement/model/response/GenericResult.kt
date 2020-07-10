package mx.rokegcode.ordermanagement.model.response

sealed class GenericResult<out R> {
    data class Success<out T>(val data: T) : GenericResult<T>()
    data class Error(val error: String) : GenericResult<Nothing>()
    data class Loading(val message: String) : GenericResult<Nothing>()
    object LoginActivity : GenericResult<Nothing>()
}