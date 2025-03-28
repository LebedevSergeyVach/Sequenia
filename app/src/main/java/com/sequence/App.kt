package com.sequence

import android.app.Application
import com.sequence.di.apiModule
import com.sequence.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Указываем контекст приложения
            androidContext(this@App)
            // Подключаем все модули DI
            modules(
                apiModule,
                repositoryModule
            )
        }
    }
}
