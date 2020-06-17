package mx.rokegcode.ordermanagement.model.db

import androidx.room.*
import mx.rokegcode.ordermanagement.model.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE userName = :userName AND userPassword = :userPassword")
    fun getUser(userName: String, userPassword: String) : User

    @Query("DELETE FROM user WHERE idUser = :idUser")
    fun deleteById(idUser: Long)

}