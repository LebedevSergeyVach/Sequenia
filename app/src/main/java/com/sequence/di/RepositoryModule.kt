package com.sequence.di

import com.sequence.repository.FilmsRepository
import com.sequence.repository.NetworkFilmsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FilmsRepository> { NetworkFilmsRepository(get()) }
}
