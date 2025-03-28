package com.sequenia.viewmodel

import com.sequenia.data.FilmData

data class FilmsState(
    val films: List<FilmData>? = null,
    val statusFilmsState: StatusLoad = StatusLoad.Idle,
) {

    val isRefreshing: Boolean
        get() = statusFilmsState == StatusLoad.Loading && films?.isNotEmpty() == true

    val isEmptyLoading: Boolean
        get() = statusFilmsState == StatusLoad.Loading && films.isNullOrEmpty()

    val isRefreshError: Boolean
        get() = statusFilmsState is StatusLoad.Error && films?.isNotEmpty() == true

    val isEmptyError: Boolean
        get() = statusFilmsState is StatusLoad.Error && films.isNullOrEmpty()
}
