package com.sequenia

import android.app.Application

import com.sequenia.di.ApiModule
import com.sequenia.di.RepositoryModule
import com.sequenia.di.ViewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            if (BuildConfig.DEBUG) printLogger()

            modules(
                listOf(
                    ApiModule().module,
                    RepositoryModule().module,
                    ViewModelModule().module,
                )
            )
        }
    }
}
