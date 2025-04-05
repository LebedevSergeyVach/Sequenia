package com.sequenia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sequenia.BuildConfig
import com.sequenia.data.film.FilmData
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

    private var films: List<FilmData> = emptyList()

    init {
        load()
    }

    fun load() {
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
                films = repository.getListAllFilmsData()
                    .sortedBy { film: FilmData ->
                        film.localizedName
                    }

                if (BuildConfig.DEBUG) LoggerHelper.d("ViewModel: $films")

                _state.update { filmsState: FilmsState ->
                    filmsState.copy(
                        films = applyGenreFilter(films, _state.value.genre),
                        statusFilmsState = StatusLoad.Idle,
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

    fun toggleGenre(genre: String?) {
        val newGenre = if (_state.value.genre == genre) null else genre
        _state.update {
            it.copy(
                genre = newGenre,
                films = applyGenreFilter(films, newGenre)
            )
        }
    }

    private fun applyGenreFilter(
        films: List<FilmData>,
        genre: String?
    ): List<FilmData> {
        return if (genre == null) {
            films
        } else {
            films.filter { film ->
                film.genres.any { it.equals(genre, ignoreCase = true) }
            }
        }
    }

    fun getAvailableGenres(): List<String> {
        return films
            .flatMap { it.genres }
            .distinct()
            .map { genre -> genre.replaceFirstChar { it.uppercase() } }
            .sorted()
    }

    fun consumerError() {
        _state.update { filmsState: FilmsState ->
            filmsState.copy(
                statusFilmsState = StatusLoad.Idle,
            )
        }
    }
}
