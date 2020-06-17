package mx.rokegcode.ordermanagement.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order(
    var idCustomer: Long?,
    var orderDate: String?,
    var deliveryDate: String?,
    var deliveryPlace: String?,
    var typeProduct: String?,
    var fillingProduct: String?,
    var productDesign: String?,
    var productPrice: Double?,
    var advanceOrder: Double?,
    var debitOrder: Double?,
    @PrimaryKey(autoGenerate = true)
    var idOrder: Long?
)