package mx.rokegcode.ordermanagement.model.data

import androidx.room.Embedded
import androidx.room.Relation

data class JoinOrderCustomer(
    @Embedded
    var order: Order,
    @Relation(
        parentColumn = "idCustomer",
        entityColumn = "idCustomer"
    ) var customer: Customer
)