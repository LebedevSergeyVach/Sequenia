package com.sequenia.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * [FilmsResponses] - DTO для ответа API.
 *
 * @property [filmsResponses] Список объектов FilmData
 * @see [FilmData] Для структуры данных отдельного фильма
 */
@Serializable
data class FilmsResponses(
    @SerialName("films")
    val filmsResponses: List<FilmData> = emptyList(),
)
