package mx.rokegcode.ordermanagement.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.support.DATABASE_VERSION

@Database(
    entities = [User::class, Order::class, Customer::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerDao

}