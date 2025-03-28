package com.sequence.repository

import com.sequence.api.FilmsApi
import com.sequence.data.FilmData

class NetworkFilmsRepository(
    private val api: FilmsApi
) : FilmsRepository {
    override suspend fun getListAllFilmsData(): List<FilmData> =
        api.getAllFilms()
}
