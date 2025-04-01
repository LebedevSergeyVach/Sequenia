package com.sequenia.api

import com.sequenia.data.FilmsResponses

import retrofit2.http.GET

interface FilmsApi {
    @GET("sequeniatesttask/films.json")
    suspend fun getAllFilms(): FilmsResponses
}
