package mx.rokegcode.ordermanagement.model.repository.interfaces

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.data.JoinOrderCustomer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.util.DataState

interface IOrderRepository {
    fun setOrder(order: Order): Flow<DataState<Long>>
    fun getOrders(): Flow<DataState<List<JoinOrderCustomer>>>
}