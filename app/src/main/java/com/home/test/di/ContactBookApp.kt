package com.home.test.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class ContactBookApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}