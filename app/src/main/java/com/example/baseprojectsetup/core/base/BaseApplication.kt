package com.example.baseprojectsetup.core.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        fun get(context : Context) : BaseApplication = context.applicationContext as BaseApplication
    }
}