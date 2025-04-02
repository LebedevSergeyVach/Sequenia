package com.sequenia.repository

import com.sequenia.api.FilmsApi
import com.sequenia.data.FilmData

class FilmsRepositoryImpl(
    private val api: FilmsApi
) : FilmsRepository {
    override suspend fun getListAllFilmsData(): List<FilmData> =
        api.getAllFilms().filmsResponses
}
