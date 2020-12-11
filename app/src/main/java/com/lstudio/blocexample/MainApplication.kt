package com.lstudio.blocexample

import android.app.Application
import com.lstudio.blocexample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    init {
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}