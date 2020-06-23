package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.ViewModel
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository

class MainViewModel(private val orderRepository: IOrderRepository): ViewModel() {
}