package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.repository.interfaces.ICustomerRepository
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.util.DataState

class OrderViewModel(
    private val orderRepository: IOrderRepository,
    private val customerRepository: ICustomerRepository
) : ViewModel() {

    private val _customerList: MutableLiveData<DataState<List<Customer>>> = MutableLiveData()
    var customerList: LiveData<DataState<List<Customer>>> = _customerList

    private val _customerInsert: MutableLiveData<DataState<Long>> = MutableLiveData()
    var customerInsert: LiveData<DataState<Long>> = _customerInsert

    fun onCreateOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.setOrder(order).collect {
                //_dataState.value = dataState
            }
        }
    }

    fun setCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.setCustomer(customer).collect {
                _customerInsert.value = it
            }
        }
    }

    fun getCustomers() {
        viewModelScope.launch {
            customerRepository.getCustomers().collect {
                _customerList.value = it
            }
        }
    }

    /*fun setStateEvent(orderStateEvent: OrderStateEvent) {
        viewModelScope.launch {
            when (orderStateEvent) {
                is OrderStateEvent.GetCustomers -> {
                    customerRepository.getCustomers().collect { dataState ->
                        _dataState.value = dataState
                    }
                }
                is OrderStateEvent.SetCustomer -> {
                    customerRepository.setCustomer().collect { dataState ->

                    }
                }
            }
        }
    }*/

}

/*sealed class OrderStateEvent<out R> {
    object GetCustomers : OrderStateEvent<Nothing>()
    data class SetCustomer<out T>(val customer: Customer) : OrderStateEvent<T>()
    object None : OrderStateEvent<Nothing>()
}*/

