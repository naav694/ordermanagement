package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE userName = :userName AND userPassword = :userPassword")
    suspend fun getUser(userName: String, userPassword: String) : User

    @Query("DELETE FROM user WHERE idUser = :idUser")
    suspend fun deleteById(idUser: Long)

}