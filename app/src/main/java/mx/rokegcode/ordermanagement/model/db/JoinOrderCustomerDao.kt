package mx.rokegcode.ordermanagement.model.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import mx.rokegcode.ordermanagement.model.data.JoinOrderCustomer

@Dao
interface JoinOrderCustomerDao {

    @Transaction
    @Query("SELECT * FROM `order`")
    suspend fun getOrders() : List<JoinOrderCustomer>

}