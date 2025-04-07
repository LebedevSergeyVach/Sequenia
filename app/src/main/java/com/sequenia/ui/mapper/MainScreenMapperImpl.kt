package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItemSealed
import com.sequenia.data.FilmData
import okhttp3.internal.toImmutableList

/**
 * Реализация интерфейса [MainScreenMapper].
 * Преобразует данные в элементы `UI` с учетом выбранного жанра.
 */
object MainScreenMapperImpl : MainScreenMapper {

    /**
     * Реализация метода маппинга данных в `UI`-элементы.
     *
     * Создает список, содержащий:
     * 1. Заголовок жанров
     * 2. Список жанров (с отметкой выбранного)
     * 3. Заголовок фильмов
     * 4. Список фильмов (отфильтрованный по выбранному жанру, если указан)
     *
     * @param genres Список доступных жанров.
     * @param films Список фильмов (может быть `null`).
     * @param selectedGenre Выбранный жанр (может быть `null`).
     * @param genresTitle Заголовок для секции жанров.
     * @param filmsTitle Заголовок для секции фильмов.
     *
     * @return Неизменяемый список элементов `UI`.
     */
    override fun map(
        genres: List<String>,
        films: List<FilmData>?,
        selectedGenre: String?,
        genresTitle: String,
        filmsTitle: String,
    ): List<MainScreenItemSealed>
        = mutableListOf<MainScreenItemSealed>().apply {
            add(MainScreenItemSealed.Header(title = genresTitle))

            genres.forEach { genre: String ->
                add(
                    MainScreenItemSealed.Genre(
                        name = genre,
                        isSelected = genre.equals(other = selectedGenre, ignoreCase = true)
                    )
                )
            }

            add(MainScreenItemSealed.Header(title = filmsTitle))

            films?.let { filmList: List<FilmData> ->
                addAll(filmList.map { film: FilmData ->
                    MainScreenItemSealed.Film(film)
                })
            }
        }.toImmutableList()
}
