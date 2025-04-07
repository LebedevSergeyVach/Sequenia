package com.sequenia.viewmodel

import com.sequenia.data.FilmData

/**
 * Класс состояния экрана с фильмами.
 * Содержит данные о фильмах, текущий выбранный жанр и статус загрузки.
 *
 * @property films Список фильмов для отображения (может быть null).
 * @property genre Текущий выбранный жанр (может быть null).
 * @property statusFilmsState Текущий статус загрузки данных.
 */
data class FilmsState(
    val films: List<FilmData>? = null,
    val genre: String? = null,
    val statusFilmsState: StatusLoad = StatusLoad.Idle,
) {

    /**
     * Проверяет, идет ли загрузка при пустом списке фильмов.
     *
     * @return true если статус [Loading] и список фильмов пуст или null.
     */
    val isEmptyLoading: Boolean
        get() = statusFilmsState == StatusLoad.Loading && films.isNullOrEmpty()

    /**
     * Проверяет, произошла ли ошибка при пустом списке фильмов.
     *
     * @return true если статус [Error] и список фильмов пуст или null.
     */
    val isEmptyError: Boolean
        get() = statusFilmsState is StatusLoad.Error && films.isNullOrEmpty()
}
