package mx.rokegcode.ordermanagement.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    var fullName: String?,
    var userName: String?,
    var userPassword: String?,
    @PrimaryKey(autoGenerate = true)
    var idUser: Long?
) : Parcelable