package com.example.famousmovies

import android.app.Application
import com.example.famousmovies.data.MovieAppContainer

class MovieApplication:Application() {
    lateinit var container:MovieAppContainer
    override fun onCreate() {
        super.onCreate()
        container=MovieAppContainer()
    }
}