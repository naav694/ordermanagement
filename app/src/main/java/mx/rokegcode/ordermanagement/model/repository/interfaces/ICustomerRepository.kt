package mx.rokegcode.ordermanagement.model.repository.interfaces

import kotlinx.coroutines.flow.Flow
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.util.DataState

interface ICustomerRepository {
    fun getCustomers(): Flow<DataState<List<Customer>>>
    fun setCustomer(customer: Customer): Flow<DataState<List<Customer>>>
}