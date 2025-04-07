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

/**
 * [ViewModel] для фрагмента с фильмами.
 * Отвечает за загрузку данных, фильтрацию по жанрам и управление состоянием экрана.
 *
 * @param repository Репозиторий для работы с данными фильмов.
 *
 * @property [KoinViewModel] Аннотация для интеграции с `Koin DI`.
 */
@KoinViewModel
class FilmsViewModel(
    private val repository: FilmsRepository,
) : ViewModel() {

    /**
     * Приватное состояние [FilmsViewModel]
     *
     * @see [MutableStateFlow].
     */
    private val _state: MutableStateFlow<FilmsState> = MutableStateFlow(FilmsState())

    /**
     * Публичное неизменяемое состояние [FilmsViewModel].
     *
     * @return Текущее состояние типа [FilmsState].
     *
     * @see [StateFlow].
     */
    val state: StateFlow<FilmsState> = _state.asStateFlow()

    private var allFilms: List<FilmData> = emptyList()

    init {
        load()
    }

    /**
     * Загрузка всех данныз из [FilmsViewModel].
     */
    fun load() {
        getListAllFilmsData()
    }

    /**
     * Загружает список всех фильмов из репозитория.
     * Обновляет состояние в процессе загрузки и при ее завершении.
     *
     * @see [FilmsRepository.getListAllFilmsData]
     */
    private fun getListAllFilmsData() {
        _state.update { filmsState: FilmsState ->
            filmsState.copy(
                statusFilmsState = StatusLoad.Loading,
            )
        }

        viewModelScope.launch {
            try {
                allFilms = repository.getListAllFilmsData()
                    .sortedBy { film: FilmData ->
                        film.localizedName
                    }

                if (BuildConfig.DEBUG) LoggerHelper.d("ViewModel: $allFilms")

                _state.update { filmsState: FilmsState ->
                    filmsState.copy(
                        films = applyGenreFilter(allFilms, _state.value.genre),
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

    /**
     * Переключает выбранный жанр.
     * Если передается тот же жанр, что уже выбран - сбрасывает выбор.
     *
     * @param genre Жанр для переключения (может быть null).
     */
    fun toggleGenre(genre: String?) {
        val newGenre = if (_state.value.genre == genre) null else genre

        _state.update { stateFilmS: FilmsState ->
            stateFilmS.copy(
                films = applyGenreFilter(films = allFilms, genre = newGenre),
                genre = newGenre,
            )
        }
    }

    /**
     * Применяет фильтрацию списка фильмов по жанру.
     *
     * @param films Исходный список фильмов.
     * @param genre Жанр для фильтрации (может быть null).
     *
     * @return Отфильтрованный список фильмов или исходный список если жанр null.
     */
    private fun applyGenreFilter(
        films: List<FilmData>,
        genre: String?
    ): List<FilmData> =
        if (genre == null) {
            films
        } else {
            films.filter { film: FilmData ->
                film.genres.any { filterFromGenre: String ->
                    filterFromGenre.equals(other = genre, ignoreCase = true)
                }
            }
        }

    /**
     * Возвращает список всех доступных жанров из загруженных фильмов.
     *
     * @return Список уникальных жанров в алфавитном порядке.
     */
    fun getAvailableGenres(): List<String> =
        allFilms
            .flatMap { film: FilmData ->
                film.genres
            }
            .distinct()
            .map { genre: String ->
                genre.replaceFirstChar { firstCharacter: Char ->
                    firstCharacter.uppercase()
                }
            }
            .sorted()
}
