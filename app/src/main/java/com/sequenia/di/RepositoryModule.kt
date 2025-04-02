package com.sequenia.di

import com.sequenia.api.FilmsApi
import com.sequenia.repository.FilmsRepository
import com.sequenia.repository.FilmsRepositoryImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class RepositoryModule {

    @Single
    fun provideFilmsRepository(api: FilmsApi): FilmsRepository = FilmsRepositoryImpl(api)
}
