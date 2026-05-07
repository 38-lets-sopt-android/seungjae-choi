package com.example.letssopt.core.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object AuthPreference {
    private const val PREF_NAME = "auth_pref"
    private const val KEY_EMAIL = "email"
    private const val KEY_PASSWORD = "password"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        if (!::prefs.isInitialized) {
            prefs = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveAccount(email: String, password: String) {
        prefs.edit {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
        }
    }

    fun saveUserId(id: Long) {
        prefs.edit { putLong(KEY_USER_ID, id) }
    }

    fun getUserId(): Long = prefs.getLong(KEY_USER_ID, -1L)

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
}