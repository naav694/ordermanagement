package mx.rokegcode.ordermanagement.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var fullName: String = "",
    var userName: String = "",
    var userPassword: String = "",
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0
)