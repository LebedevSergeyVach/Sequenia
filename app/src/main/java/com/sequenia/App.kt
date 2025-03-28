package com.sequenia

import android.app.Application
import com.sequenia.di.ApiModule
import com.sequenia.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                ApiModule().module,
                RepositoryModule().module,
            )
        }
    }
}
