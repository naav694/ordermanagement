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
import mx.rokegcode.ordermanagement.view.state.OrderStateEvent

class OrderViewModel(
    private val orderRepository: IOrderRepository,
    private val customerRepository: ICustomerRepository
) : ViewModel() {
    private val _customerList = MutableLiveData<DataState<List<Customer>>>()
    val customerList: LiveData<DataState<List<Customer>>> get() = _customerList

    private val _customerInsert = MutableLiveData<DataState<List<Customer>>>()
    val customerInsert: LiveData<DataState<List<Customer>>> get() = _customerInsert

    fun onCreateOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.setOrder(order).collect {
                //_dataState.value = dataState
            }
        }
    }

    fun setStateEvent(orderStateEvent: OrderStateEvent) {
        viewModelScope.launch {
            when (orderStateEvent) {
                is OrderStateEvent.GetCustomers -> {
                    customerRepository.getCustomers().collect { dataState ->
                        _customerList.value = dataState
                    }
                }
                is OrderStateEvent.SetCustomer -> {
                    customerRepository.setCustomer(orderStateEvent.customer).collect { dataState ->
                        _customerInsert.value = dataState
                    }
                }
            }
        }
    }

}

