package mx.rokegcode.ordermanagement.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.db.OrderDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.model.response.GenericResult

class OrderRepository(private val orderDao: OrderDao) : IOrderRepository {
    @ExperimentalCoroutinesApi
    override fun setOrder(order: Order): Flow<GenericResult<Long>> = flow {
        emit(GenericResult.Loading("Creating order..."))
        emit(GenericResult.Success(orderDao.insert(order)))
    }.flowOn(Dispatchers.IO).catch {
        emit(GenericResult.Error(it.message!!))
    }


    @ExperimentalCoroutinesApi
    override fun getOrder(): Flow<GenericResult<List<Order>>> = flow {
        emit(GenericResult.Loading("Getting orders..."))
        emit(GenericResult.Success(orderDao.getOrder()))
    }.flowOn(Dispatchers.IO).catch {
        emit(GenericResult.Error(it.message!!))
    }
}