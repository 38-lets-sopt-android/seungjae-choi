package com.example.letssopt

import android.app.Application
import com.example.letssopt.core.data.AuthPreference

class LETSSOPTApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AuthPreference.init(this)
    }
}