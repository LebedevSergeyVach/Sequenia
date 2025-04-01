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

@Module
class ApiModule {
    @Single
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Single
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
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

    @Single
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_SERVER_FILM)
            .addConverterFactory(provideJson().asConverterFactory("application/json".toMediaType()))
            .build()

    @Single
    fun provideFilmsApi(retrofit: Retrofit): FilmsApi = retrofit.create()
}
