package com.sequence.di

import android.app.Application
import com.sequence.api.FilmsApi
import com.sequenia.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit

val apiModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single<OkHttpClient> {
        val cacheDir = File(get<Application>().cacheDir, "http_cache")
        val cacheSize = 10L * 1024 * 1024
        val cache = Cache(cacheDir, cacheSize)

        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
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
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.URL_SERVER_FILM)
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<FilmsApi> { get<Retrofit>().create() }
}
