package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.JoinOrderCustomer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.db.JoinOrderCustomerDao
import mx.rokegcode.ordermanagement.model.db.OrderDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.util.DataState

class OrderRepository(
    private val orderCustomerDao: JoinOrderCustomerDao,
    private val orderDao: OrderDao
    ) : IOrderRepository {

    override fun setOrder(order: Order): Flow<DataState<Long>> = flow {
        emit(DataState.Loading("Creating order..."))
        delay(1000)
        try {
            val response = orderDao.insert(order)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun getOrders(): Flow<DataState<List<JoinOrderCustomer>>> = flow {
        emit(DataState.Loading("Getting orders..."))
        delay(1000)
        try {
            val response = orderCustomerDao.getOrders()
            emit(DataState.Success(response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}