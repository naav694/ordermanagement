package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.db.OrderDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.model.response.DataState

class OrderRepository(private val orderDao: OrderDao) : IOrderRepository {

    override fun setOrder(order: Order): Flow<DataState<Long>> = flow {
        emit(DataState.Loading("Creating order..."))
        try {
            val response = orderDao.insert(order)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e.message!!))
        }
    }

    override fun getOrders(): Flow<DataState<List<Order>>> = flow {
        emit(DataState.Loading("Getting orders..."))
        try {
            val response = orderDao.getOrder()
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e.message!!))
        }
    }
}