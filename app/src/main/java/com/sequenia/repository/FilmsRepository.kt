package com.sequenia.repository

import com.sequenia.data.FilmData

/**
 * [FilmsRepository] - абстракция для доступа к данным о фильмах.
 *
 * Определяет контракт для получения данных независимо от источника.
 */
interface FilmsRepository {

    /**
     * Получает список всех фильмов.
     *
     * @return List<[FilmData]> Cписок фильмов
     */
    suspend fun getListAllFilmsData(): List<FilmData>
}
