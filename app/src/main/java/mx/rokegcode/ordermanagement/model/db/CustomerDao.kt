package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.Customer

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(customer: Customer)

    @Update
    fun update(customer: Customer)

    @Query("DELETE FROM customer WHERE idCustomer = :idCustomer")
    fun deleteById(idCustomer: Long)
}