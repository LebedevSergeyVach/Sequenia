package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItem
import com.sequenia.data.FilmData
import com.sequenia.data.GenreData

interface MainScreenMapper {

    fun map(
        genres: List<GenreData>,
        films: List<FilmData>?,
        genresTitle: String,
        filmsTitle: String
    ): List<MainScreenItem>
}
