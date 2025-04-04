package com.sequenia.ui.mapper

import com.sequenia.adapter.main.MainScreenItem
import com.sequenia.data.FilmData
import com.sequenia.data.GenreData

object MainScreenMapperImpl : MainScreenMapper {

    override fun map(
        genres: List<GenreData>, films: List<FilmData>?, genresTitle: String, filmsTitle: String
    ): List<MainScreenItem> {
        return mutableListOf<MainScreenItem>().apply {
            add(MainScreenItem.Header(title = genresTitle))

            genres.forEachIndexed { index: Int, genre: GenreData ->
                add(MainScreenItem.Genre(name = genre.name, id = index))
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
