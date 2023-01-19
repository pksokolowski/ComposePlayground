package com.github.pksokolowski.composeplayground

import android.app.Application
import com.github.pksokolowski.composeplayground.di.applicationModule
import com.github.pksokolowski.composeplayground.di.samplesModule
import com.github.pksokolowski.featurea.di.featureAModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(applicationModule, samplesModule, featureAModule)
        }

    }
}