package mx.rokegcode.ordermanagement.model.response

sealed class GenericResult<out R> {
    data class Success<out T>(val data: T) : GenericResult<T>()
    object Loading : GenericResult<Nothing>()
    object Error : GenericResult<Nothing>()
}