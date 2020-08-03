package mx.rokegcode.ordermanagement.view.state

import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order

sealed class OrderStateEvent {
    data class SetCustomer(val customer: Customer) : OrderStateEvent()
    data class SetOrder(val order: Order) : OrderStateEvent()
    object GetCustomers : OrderStateEvent()
    object None : OrderStateEvent()
}