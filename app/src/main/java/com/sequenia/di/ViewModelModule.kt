package com.sequenia.di

import com.sequenia.repository.FilmsRepository
import com.sequenia.viewmodel.FilmsViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Module

@Module
class ViewModelModule {

    @KoinViewModel
    fun provideFilmsViewModel(repository: FilmsRepository): FilmsViewModel =
        FilmsViewModel(repository)

}
