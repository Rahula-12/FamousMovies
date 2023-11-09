package com.example.famousmovies.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MoviesApiService {
    @Headers(
        "X-RapidAPI-Key: 1e59c5254cmsh65f87366aa4844fp138d18jsn4df36025d134",
        "X-RapidAPI-Host: imdb-top-100-movies.p.rapidapi.com"
    )
    @GET("/")
    fun getTopMovies():Deferred<List<Movie>>
}

const val BASE_URL="https://imdb-top-100-movies.p.rapidapi.com"

val retrofit= Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

object MoviesApi{
    val retrofitService:MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}