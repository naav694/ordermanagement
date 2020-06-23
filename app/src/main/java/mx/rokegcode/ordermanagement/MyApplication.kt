package mx.rokegcode.ordermanagement

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import androidx.room.Room
import mx.rokegcode.ordermanagement.model.db.AppDatabase
import mx.rokegcode.ordermanagement.model.repository.OrderRepository
import mx.rokegcode.ordermanagement.model.repository.interfaces.IUserRepository
import mx.rokegcode.ordermanagement.model.repository.UserRepository
import mx.rokegcode.ordermanagement.model.repository.interfaces.IOrderRepository
import mx.rokegcode.ordermanagement.receiver.NotificationReceiver
import mx.rokegcode.ordermanagement.support.CHANNEL_ID
import mx.rokegcode.ordermanagement.support.DATABASE_NAME
import mx.rokegcode.ordermanagement.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(viewModelModule, databaseModule, repositoryModule, sharedPreferencesModule)
        }

        registerBroadcast()
    }

    private fun registerBroadcast() {
        val filter = IntentFilter("$packageName.receiver.notification")
        val myReceiver = NotificationReceiver()
        registerReceiver(myReceiver, filter)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descrText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descrText
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private val viewModelModule = module {
        viewModel { SplashViewModel(get()) }
        viewModel { LoginViewModel(get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { OrderViewModel(get()) }
    }

    private val repositoryModule = module {
        factory<IUserRepository> { UserRepository(get()) }
        factory<IOrderRepository> { OrderRepository(get()) }
    }

    private val databaseModule = module {

        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single { get<AppDatabase>().userDao() }
        single { get<AppDatabase>().orderDao() }
        single { get<AppDatabase>().customerDao() }
    }

    private val sharedPreferencesModule = module {
        single<SharedPreferences> {
            androidContext().getSharedPreferences("SharedLectura", Context.MODE_PRIVATE)
        }
    }

}