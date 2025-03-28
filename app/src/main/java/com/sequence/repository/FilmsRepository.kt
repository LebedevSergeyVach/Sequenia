package com.sequence.repository

import com.sequence.data.FilmData

interface FilmsRepository {
    suspend fun getListAllFilmsData(): List<FilmData>
}
