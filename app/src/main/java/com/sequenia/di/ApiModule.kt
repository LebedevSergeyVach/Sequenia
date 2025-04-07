package com.sequenia.di

import com.sequenia.BuildConfig
import com.sequenia.api.FilmsApi

import kotlinx.serialization.json.Json

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

import java.util.concurrent.TimeUnit

/**
 * Модуль для предоставления зависимостей, связанных с `API` и сетью.
 *
 * @see Module Аннотация `Koin` для объявления модуля.
 */
@Module
class ApiModule {

    /**
     * Предоставляет экземпляр [Json] с настройками по умолчанию.
     *
     * @return Настроенный экземпляр [Json].
     *
     * @property ignoreUnknownKeys Игнорировать неизвестные ключи при парсинге.
     * @property coerceInputValues Приводить несоответствующие значения к корректным типам.
     *
     * @single Аннотация `Koin` для создания `singleton`-объекта.
     */
    @Single
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    /**
     * Предоставляет настроенный экземпляр [OkHttpClient].
     * В `debug`-режиме добавляет логгирование запросов.
     * Устанавливает таймауты и кеширование.
     *
     * @return Настроенный [OkHttpClient].
     *
     * @property connectTimeout Таймаут соединения (30 секунд).
     * @property readTimeout Таймаут чтения (30 секунд).
     *
     * @see [HttpLoggingInterceptor] Для логгирования в `debug`-режиме.
     */
    @Single
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .let { clientOkHttp: OkHttpClient.Builder ->
                if (BuildConfig.DEBUG) {
                    clientOkHttp.addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                } else {
                    clientOkHttp
                }
            }
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .header("Cache-Control", "public, max-age=60")
                        .build()
                )
            }
            .build()

    /**
     * Предоставляет экземпляр [Retrofit] с базовыми настройками.
     *
     * @param [okHttpClient] Клиент для сетевых запросов.
     *
     * @return Настроенный экземпляр [Retrofit].
     *
     * @property baseUrl Базовый `URL` из [BuildConfig].
     * @property addConverterFactory Добавляет конвертер для работы с [JSON].
     */
    @Single
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_SERVER_FILM)
            .addConverterFactory(provideJson().asConverterFactory("application/json".toMediaType()))
            .build()

    /**
     * Предоставляет `API` для работы с фильмами.
     *
     * @param retrofit Экземпляр [Retrofit].
     * @return Реализация [FilmsApi].
     */
    @Single
    fun provideFilmsApi(retrofit: Retrofit): FilmsApi = retrofit.create()
}
