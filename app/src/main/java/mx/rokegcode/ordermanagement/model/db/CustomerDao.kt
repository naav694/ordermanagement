package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM CUSTOMER ORDER BY customerName ASC")
    suspend fun getCustomers() : List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCustomer(customer: Customer)

    @Update
    suspend fun update(customer: Customer)

    @Query("DELETE FROM customer WHERE idCustomer = :idCustomer")
    suspend fun deleteById(idCustomer: Long)
}