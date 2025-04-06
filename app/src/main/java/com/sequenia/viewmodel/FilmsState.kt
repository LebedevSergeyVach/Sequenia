package com.sequenia.viewmodel

import com.sequenia.data.film.FilmData

data class FilmsState(
    val films: List<FilmData>? = null,
    val genre: String? = null,
    val statusFilmsState: StatusLoad = StatusLoad.Idle,
) {
    val isEmptyLoading: Boolean
        get() = statusFilmsState == StatusLoad.Loading && films.isNullOrEmpty()

    val isEmptyError: Boolean
        get() = statusFilmsState is StatusLoad.Error && films.isNullOrEmpty()
}
