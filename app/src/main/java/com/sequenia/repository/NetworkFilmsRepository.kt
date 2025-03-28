package com.sequenia.repository

import com.sequenia.api.FilmsApi
import com.sequenia.data.FilmData

class NetworkFilmsRepository(
    private val api: FilmsApi
) : FilmsRepository {
    override suspend fun getListAllFilmsData(): List<FilmData> =
        api.getAllFilms()
}
