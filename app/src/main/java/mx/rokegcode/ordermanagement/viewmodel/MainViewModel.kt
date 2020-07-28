package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.util.DataState
import mx.rokegcode.ordermanagement.util.interfaces.ISessionHelper

class MainViewModel(
    private val orderRepository: IOrderRepository,
    sessionHelper: ISessionHelper
) : BaseViewModel(sessionHelper) {

    private val _resultOrders = MutableLiveData<DataState<List<Order>>>()
    val resultOrders: LiveData<DataState<List<Order>>> get() = _resultOrders

    fun getOrders() {
        viewModelScope.launch {
            orderRepository.getOrders().collect {
                _resultOrders.value = it
            }
        }
    }

}