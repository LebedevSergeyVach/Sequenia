package com.sequenia.repository

import com.sequenia.data.FilmData

interface FilmsRepository {
    suspend fun getListAllFilmsData(): List<FilmData>
}
