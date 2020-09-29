package mx.rokegcode.ordermanagement.model.repository

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.api.response.OrderResponse
import mx.rokegcode.ordermanagement.model.data.JoinOrderCustomer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.util.DataState

class FakeOrderRepository : IOrderRepository {

    override fun setOrder(order: Order): Flow<DataState<Long>> {
        TODO("Not yet implemented")
    }

    override fun getOrders(): Flow<DataState<List<JoinOrderCustomer>>> {
        TODO("Not yet implemented")
    }

    override fun uploadOrders(orders: List<Order>): Flow<DataState<OrderResponse>> {
        TODO("Not yet implemented")
    }


}