package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItem
import com.sequenia.data.film.FilmData

object MainScreenMapperImpl : MainScreenMapper {

    override fun map(
        genres: List<String>,
        films: List<FilmData>?,
        selectedGenre: String?,
        genresTitle: String,
        filmsTitle: String,
    ): List<MainScreenItem> {
        return mutableListOf<MainScreenItem>().apply {
            add(MainScreenItem.Header(title = genresTitle))

            genres.forEach { genre: String ->
                add(
                    MainScreenItem.Genre(
                        name = genre,
                        isSelected = genre.equals(other = selectedGenre, ignoreCase = true)
                    )
                )
            }

            add(MainScreenItem.Header(title = filmsTitle))

            films?.let { filmList: List<FilmData> ->
                addAll(filmList.map { film: FilmData ->
                    MainScreenItem.Film(film)
                })
            }
        }
    }
}
