package com.sequenia.repository

import com.sequenia.api.FilmsApi
import com.sequenia.data.FilmData

/**
 * [FilmsRepositoryImpl] - реализация [FilmsRepository].
 *
 * @property api Экземпляр [FilmsApi] для сетевых запросов.
 */
class FilmsRepositoryImpl(
    private val api: FilmsApi
) : FilmsRepository {

    /**
     * Реализация получения списка фильмов.
     *
     * @return List<[FilmData]> Список фильмов
     */
    override suspend fun getListAllFilmsData(): List<FilmData> =
        api.getAllFilms().filmsResponses
}
