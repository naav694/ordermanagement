package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM `ORDER`")
    suspend fun getOrder(): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order): Long

    @Update
    fun update(order: Order)

    @Query("DELETE FROM `order` WHERE idOrder = :idOrder")
    fun deleteById(idOrder: Long)

}