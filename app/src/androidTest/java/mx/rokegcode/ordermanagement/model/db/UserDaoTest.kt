package mx.rokegcode.ordermanagement.model.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import mx.rokegcode.ordermanagement.model.data.Order
import mx.rokegcode.ordermanagement.model.data.User
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = appDatabase.userDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertAndDeleteUserTest() = runBlockingTest {
        val userTest = User(
            "Edgar Ivan Rodriguez Garcia",
            "enavi",
            "enavi",
            1
        )
        userDao.insert(userTest)
        val user = userDao.getUser(
            "enavi",
            "enavi"
        )
        assertThat(user).isEqualTo(userTest)
        userDao.deleteById(1)
        val userAfter = userDao.getUser(
            "enavi",
            "enavi"
        )
        assertThat(userAfter).isNull()
    }

}