package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.repository.interfaces.ICustomerRepository
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.util.DataState

class OrderViewModel(
    private val orderRepository: IOrderRepository,
    private val customerRepository: ICustomerRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<Long>> = MutableLiveData()

    var dataState: LiveData<DataState<Long>> = _dataState

    fun onCreateOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.setOrder(order).collect { dataState ->
                _dataState.value = dataState
            }
        }
    }

    fun setStateEvent(orderStateEvent: OrderStateEvent) {
        viewModelScope.launch {
            when(orderStateEvent) {
                is OrderStateEvent -> {
                    customerRepository
                }
            }
        }
    }

}

sealed class OrderStateEvent {
    object GetCustomers: OrderStateEvent()
    object None: OrderStateEvent()
}

