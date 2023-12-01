package com.example.famousmovies.retrofit

import com.example.famousmovies.model.MovieItem
import retrofit2.http.GET
import retrofit2.http.Headers
interface MoviesAPI {

    @GET("/")
    @Headers("X-RapidAPI-Key:1e59c5254cmsh65f87366aa4844fp138d18jsn4df36025d134","X-RapidAPI-Host:imdb-top-100-movies.p.rapidapi.com")
    suspend fun getMovies():List<MovieItem>

}