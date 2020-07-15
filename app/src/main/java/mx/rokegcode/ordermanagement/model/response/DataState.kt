package mx.rokegcode.ordermanagement.model.response

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: String) : DataState<Nothing>()
    data class Loading(val message: String) : DataState<Nothing>()
    object LoginActivity : DataState<Nothing>()
}