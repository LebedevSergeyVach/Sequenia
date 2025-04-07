package com.sequenia.di

import com.sequenia.repository.FilmsRepository
import com.sequenia.viewmodel.FilmsViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Module

/**
 * Модуль для предоставления ViewModel.
 *
 * @see Module Аннотация `Koin` для объявления модуля.
 */
@Module
class ViewModelModule {

    /**
     * Предоставляет `ViewModel` для экрана фильмов.
     *
     * @param repository Репозиторий для работы с данными.
     *
     * @return Экземпляр [FilmsViewModel].
     *
     * @KoinViewModel Аннотация для интеграции `ViewModel` с `Koin`.
     */
    @KoinViewModel
    fun provideFilmsViewModel(repository: FilmsRepository): FilmsViewModel =
        FilmsViewModel(repository)
}
