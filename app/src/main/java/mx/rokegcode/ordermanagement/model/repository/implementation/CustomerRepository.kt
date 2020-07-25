package mx.rokegcode.ordermanagement.model.repository.implementation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.db.CustomerDao
import mx.rokegcode.ordermanagement.model.repository.interfaces.ICustomerRepository
import mx.rokegcode.ordermanagement.util.DataState

class CustomerRepository(private val customerDao: CustomerDao) : ICustomerRepository {

    override fun getCustomers(): Flow<DataState<List<Customer>>> = flow {
        emit(DataState.Loading("Loading customers..."))
        delay(1000)
        try {
            emit(DataState.Success(customerDao.getCustomers()))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun setCustomer(customer: Customer): Flow<DataState<List<Customer>>> = flow {
        emit(DataState.Loading("Adding customer..."))
        delay(1000)
        try {
            customerDao.setCustomer(customer)
            emit(DataState.Success(customerDao.getCustomers()))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}