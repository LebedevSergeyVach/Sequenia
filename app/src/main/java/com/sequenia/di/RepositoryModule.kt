package com.sequenia.di

import com.sequenia.api.FilmsApi
import com.sequenia.repository.FilmsRepository
import com.sequenia.repository.FilmsRepositoryImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Модуль для предоставления репозиториев.
 *
 * @see Module Аннотация `Koin` для объявления модуля.
 */
@Module
class RepositoryModule {

    /**
     * Предоставляет репозиторий для работы с данными фильмов.
     *
     * @param api `API` для работы с фильмами.
     *
     * @return Реализация [FilmsRepository].
     *
     * @see FilmsRepository Интерфейс репозитория.
     * @see FilmsRepositoryImpl Реализация репозитория.
     */
    @Single
    fun provideFilmsRepository(api: FilmsApi): FilmsRepository = FilmsRepositoryImpl(api)
}
