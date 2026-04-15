package com.example.letssopt.core.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AuthPreference(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveAccount(email: String, password: String) {
        prefs.edit {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
        }
    }

    fun getEmail(): String = prefs.getString(KEY_EMAIL, "") ?: ""

    fun getPassword(): String = prefs.getString(KEY_PASSWORD, "") ?: ""

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    companion object {
        private const val PREF_NAME = "auth_pref"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}