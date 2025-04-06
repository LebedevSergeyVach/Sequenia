package com.sequenia.data.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponses(
    @SerialName("films")
    val filmsResponses: List<FilmData> = emptyList(),
)