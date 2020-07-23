package mx.rokegcode.ordermanagement.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var idCustomer: Long = 0,
    var customerName: String = "",
    var LastName: String = "",
    var customerPhone: String = "",
    var customerAddress: String = ""
) {

    override fun toString(): String {
        return "$customerName $LastName"
    }
}