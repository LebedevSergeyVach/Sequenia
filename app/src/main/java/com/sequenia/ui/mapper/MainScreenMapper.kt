package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItem
import com.sequenia.data.film.FilmData

interface MainScreenMapper {

    fun map(
        genres: List<String>,
        films: List<FilmData>?,
        selectedGenre: String?,
        genresTitle: String,
        filmsTitle: String,
    ): List<MainScreenItem>
}
