package mx.rokegcode.ordermanagement.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    var customerName: String = "",
    var lastName: String = "",
    var customerPhone: String = "",
    var customerAddress: String = "",
    @PrimaryKey(autoGenerate = true)
    var idCustomer: Long = 0
) {
    override fun toString(): String {
        return "$customerName $lastName"
    }
}