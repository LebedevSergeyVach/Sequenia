package com.sequence.api

import com.sequence.data.FilmData

import retrofit2.http.GET

interface FilmsApi {
    @GET("sequeniatesttask/films.json")
    suspend fun getAllFilms(): List<FilmData>
}
