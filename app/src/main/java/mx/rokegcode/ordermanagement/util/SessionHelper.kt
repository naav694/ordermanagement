package mx.rokegcode.ordermanagement.util

import android.content.SharedPreferences
import com.google.gson.Gson
import mx.rokegcode.ordermanagement.model.data.User
import mx.rokegcode.ordermanagement.util.interfaces.ISessionHelper

class SessionHelper(private val sharedPreferences: SharedPreferences, private val gson: Gson) :
    ISessionHelper {

    override fun getUserSession(): User {
        val jsonUser = sharedPreferences.getString(SESSION_USER, "")
        return gson.fromJson(jsonUser, User::class.java)
    }

    override fun setUserSession(user: User) {
        with(sharedPreferences.edit()) {
            val jsonUser = gson.toJson(user)
            putString(SESSION_USER, jsonUser)
            commit()
        }
    }

    override fun deleteUserSession() {
        with(sharedPreferences.edit()) {
            putString(SESSION_USER, "")
            commit()
        }
    }

    override fun setRememberSession(remember: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(REMEMBER_SESSION, remember)
            commit()
        }
    }

    override fun getRememberSession(): Boolean {
        return sharedPreferences.getBoolean(REMEMBER_SESSION, false)
    }
}