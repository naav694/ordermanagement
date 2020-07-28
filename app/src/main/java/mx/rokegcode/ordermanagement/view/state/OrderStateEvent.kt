package mx.rokegcode.ordermanagement.view.state

import mx.rokegcode.ordermanagement.model.data.Customer

sealed class OrderStateEvent {
    data class SetCustomer(val customer: Customer) : OrderStateEvent()
    object GetCustomers : OrderStateEvent()
    object None : OrderStateEvent()
}