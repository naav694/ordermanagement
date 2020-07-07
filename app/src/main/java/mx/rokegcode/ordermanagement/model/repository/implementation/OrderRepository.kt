package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.db.OrderDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.model.response.GenericResult

class OrderRepository(private val orderDao: OrderDao) : IOrderRepository {

    override fun setOrder(order: Order): Flow<GenericResult<Long>> = flow {
        emit(GenericResult.Loading("Creating order..."))
        try {
            val response = orderDao.insert(order)
            emit(GenericResult.Success(response))
        } catch (e: Exception) {
            emit(GenericResult.Error(e.message!!))
        }
    }

    override fun getOrders(): Flow<GenericResult<List<Order>>> = flow {
        emit(GenericResult.Loading("Getting orders..."))
        try {
            val response = orderDao.getOrder()
            emit(GenericResult.Success(response))
        } catch (e: Exception) {
            emit(GenericResult.Error(e.message!!))
        }
    }
}