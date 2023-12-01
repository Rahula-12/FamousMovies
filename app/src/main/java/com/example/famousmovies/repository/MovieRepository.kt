package com.example.famousmovies.repository

import com.example.famousmovies.retrofit.MoviesAPI
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesAPI: MoviesAPI) {

    suspend fun getMovies()=moviesAPI.getMovies()

}