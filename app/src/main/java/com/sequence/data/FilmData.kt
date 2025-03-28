package com.sequence.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс, представляющий данные о фильме.
 *
 * @param id Уникальный идентификатор фильма (по умолчанию 0L).
 * @param localizedName Локализованное название фильма (по умолчанию пустая строка).
 * @param name Название фильма (по умолчанию пустая строка).
 * @param year Год выпуска фильма (по умолчанию 0).
 * @param rating Рейтинг фильма (по умолчанию 0F).
 * @param imageUrl URL изображения постера фильма (по умолчанию пустая строка).
 * @param description Описание фильма (по умолчанию пустая строка).
 * @param genres Список жанров, к которым относится фильм (по умолчанию пустой список).
 */
@Serializable
data class FilmData(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("localized_name")
    val localizedName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("year")
    val year: Int = 0,
    @SerialName("rating")
    val rating: Float = 0F,
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("genres")
    val genres: List<String> = emptyList(),
)