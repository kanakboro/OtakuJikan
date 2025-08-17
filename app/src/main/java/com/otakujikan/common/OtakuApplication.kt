package com.otakujikan.common

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OtakuApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}