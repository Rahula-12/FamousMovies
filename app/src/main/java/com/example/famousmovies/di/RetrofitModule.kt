package com.example.famousmovies.di

import com.example.famousmovies.retrofit.MoviesAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://imdb-top-100-movies.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMoviesAPI(retrofit: Retrofit) : MoviesAPI{
        return retrofit.create(MoviesAPI::class.java)
    }

}