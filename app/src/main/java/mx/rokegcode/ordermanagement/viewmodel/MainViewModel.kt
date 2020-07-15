package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.model.response.DataState
import mx.rokegcode.ordermanagement.support.interfaces.ISessionHelper

class MainViewModel(
    private val orderRepository: IOrderRepository,
    sessionHelper: ISessionHelper
) : BaseViewModel(sessionHelper) {

    private val _resultOrders = MutableLiveData<DataState<List<Order>>>()
    var resultOrders: LiveData<DataState<List<Order>>> = _resultOrders

    fun getOrders() {
        viewModelScope.launch {
            orderRepository.getOrders().collect {
                _resultOrders.value = it
            }
        }
    }

}