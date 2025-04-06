package com.sequenia.adapter.main

import com.sequenia.data.film.FilmData

sealed class MainScreenItem {
    data class Header(val title: String) : MainScreenItem()
    data class Genre(val name: String, val isSelected: Boolean = false) : MainScreenItem()
    data class Film(val data: FilmData) : MainScreenItem()
}
