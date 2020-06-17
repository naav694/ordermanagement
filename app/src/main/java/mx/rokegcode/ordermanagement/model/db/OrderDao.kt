package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.Order

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    @Query("DELETE FROM `order` WHERE idOrder = :idOrder")
    fun deleteById(idOrder: Long)

}