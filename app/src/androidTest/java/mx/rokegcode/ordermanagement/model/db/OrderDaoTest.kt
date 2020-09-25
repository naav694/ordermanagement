package mx.rokegcode.ordermanagement.model.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import mx.rokegcode.ordermanagement.model.data.Customer
import mx.rokegcode.ordermanagement.model.data.Order
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class OrderDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var orderDao: OrderDao
    private lateinit var customerDao: CustomerDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        orderDao = appDatabase.orderDao()
        customerDao = appDatabase.customerDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertAndDeleteOrderTest() = runBlockingTest {
        val customerTest = Customer(
            "Edgar Ivan",
            "Rodriguez Garcia",
            "4049515768",
            "Abbots Run"
        )
        val idCustomerTest = customerDao.setCustomer(customerTest)
        val orderTest = Order(
            idCustomerTest,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0.0,
            0.0,
            0.0,
            1
        )
        orderDao.insert(orderTest)
        val order = orderDao.getOrder()
        assertThat(order).contains(orderTest)
        orderDao.deleteById(1)
        val orderAfter = orderDao.getOrder()
        assertThat(orderAfter).doesNotContain(order)
    }

}