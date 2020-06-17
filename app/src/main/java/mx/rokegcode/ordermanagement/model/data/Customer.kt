package mx.rokegcode.ordermanagement.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var idCustomer: Long?,
    var customerName: String?,
    var customerPhone: String?,
    var customerAddress: String?
)