package com.sequenia.api

import com.sequenia.data.FilmsResponses

import retrofit2.http.GET

/**
 * FilmsApi - интерфейс для работы с API фильмов.
 *
 * @see retrofit2.http.GET Для определения GET-запросов
 * @see kotlinx.serialization.Serializable Для сериализации данных
 */
interface FilmsApi {

    /**
     * Получает список всех фильмов с сервера.
     *
     * @return [FilmsResponses] Объект-обертка с массивом фильмов
     */
    @GET("sequeniatesttask/films.json")
    suspend fun getAllFilms(): FilmsResponses
}
