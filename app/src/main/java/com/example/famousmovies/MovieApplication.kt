package com.example.famousmovies

import android.app.Application
import com.example.famousmovies.di.ApplicationComponent
import com.example.famousmovies.di.DaggerApplicationComponent

class MovieApplication:Application() {

    lateinit var component:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component=DaggerApplicationComponent.builder().build()
    }

}