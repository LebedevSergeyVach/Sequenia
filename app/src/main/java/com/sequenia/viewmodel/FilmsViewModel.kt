package com.sequenia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sequenia.BuildConfig
import com.sequenia.data.FilmData
import com.sequenia.repository.FilmsRepository
import com.sequenia.utils.helper.LoggerHelper

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FilmsViewModel(
    private val repository: FilmsRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<FilmsState> = MutableStateFlow(FilmsState())
    val state: StateFlow<FilmsState> = _state.asStateFlow()

    init {
        loadFilms()
    }

    fun loadFilms() {
        getListAllFilmsData()
    }

    private fun getListAllFilmsData() {
        _state.update { filmsState: FilmsState ->
            filmsState.copy(
                statusFilmsState = StatusLoad.Loading,
            )
        }

        viewModelScope.launch {
            try {
                val films: List<FilmData> = repository.getListAllFilmsData()

                if (BuildConfig.DEBUG) LoggerHelper.d("ViewModel: $films")

                _state.update { filmsState: FilmsState ->
                    filmsState.copy(
                        statusFilmsState = StatusLoad.Idle,
                        films = films,
                    )
                }
            } catch (e: Exception) {
                _state.update { filmsState: FilmsState ->
                    filmsState.copy(
                        statusFilmsState = StatusLoad.Error(exception = e),
                    )
                }

                if (BuildConfig.DEBUG) LoggerHelper.e("ERROR ViewModel: $e")
            }
        }
    }

    fun consumerError() {
        _state.update { filmsState: FilmsState ->
            filmsState.copy(
                statusFilmsState = StatusLoad.Idle,
            )
        }
    }
}
