package com.sequenia.adapter.main

import com.sequenia.data.FilmData

sealed class MainScreenItem {
    data class Header(val title: String) : MainScreenItem()
    data class Genre(val name: String, val id: Int) : MainScreenItem()
    data class Film(val data: FilmData) : MainScreenItem()
}
