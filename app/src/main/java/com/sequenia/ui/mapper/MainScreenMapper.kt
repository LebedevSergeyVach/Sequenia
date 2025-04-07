package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItemSealed
import com.sequenia.data.FilmData

/**
 * Интерфейс для преобразования данных в элементы `UI` главного экрана.
 */
interface MainScreenMapper {

    /**
     * Преобразует данные фильмов и жанров в список элементов для отображения.
     *
     * @param genres Список доступных жанров.
     * @param films Список фильмов (может быть `null`).
     * @param selectedGenre Выбранный жанр для фильтрации (может быть `null`).
     * @param genresTitle Заголовок секции жанров.
     * @param filmsTitle Заголовок секции фильмов.
     *
     * @return Список элементов типа [MainScreenItemSealed] для отображения в `RecyclerView`.
     *
     * @see [MainScreenItemSealed] `Sealed`-класс с типами элементов `UI`.
     */
    fun map(
        genres: List<String>,
        films: List<FilmData>?,
        selectedGenre: String?,
        genresTitle: String,
        filmsTitle: String,
    ): List<MainScreenItemSealed>
}
