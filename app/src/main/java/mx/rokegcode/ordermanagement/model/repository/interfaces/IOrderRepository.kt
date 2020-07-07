package mx.rokegcode.ordermanagement.model.repository.interfaces

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.response.GenericResult

interface IOrderRepository {

    fun setOrder(order: Order): Flow<GenericResult<Long>>
    fun getOrders(): Flow<GenericResult<List<Order>>>
}