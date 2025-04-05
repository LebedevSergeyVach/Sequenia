package com.sequenia.repository

import com.sequenia.data.film.FilmData

interface FilmsRepository {
    suspend fun getListAllFilmsData(): List<FilmData>
}
