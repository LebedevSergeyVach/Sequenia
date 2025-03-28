package com.sequenia.di

import com.sequenia.repository.FilmsRepository
import com.sequenia.repository.NetworkFilmsRepository
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class RepositoryModule {

    @Single
    fun bindFilmsRepository(implFilmsRepository: NetworkFilmsRepository): FilmsRepository =
        implFilmsRepository
}
