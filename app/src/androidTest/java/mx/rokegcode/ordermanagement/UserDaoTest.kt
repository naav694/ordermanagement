package mx.rokegcode.ordermanagement

import androidx.test.ext.junit.runners.AndroidJUnit4
import mx.rokegcode.ordermanagement.model.db.AppDatabase
import mx.rokegcode.ordermanagement.model.db.UserDao
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class UserDaoTest : KoinTest {

    val appDatabase: AppDatabase by inject()
    val userDao: UserDao by inject()


}