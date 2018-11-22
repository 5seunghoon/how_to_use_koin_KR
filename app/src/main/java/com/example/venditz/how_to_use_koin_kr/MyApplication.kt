package com.example.venditz.how_to_use_koin_kr

import android.app.Application
import com.example.venditz.how_to_use_koin_kr.di.MyAppModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, MyAppModule)
    }
}