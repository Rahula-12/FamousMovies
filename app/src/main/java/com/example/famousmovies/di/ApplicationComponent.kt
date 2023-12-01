package com.example.famousmovies.di

import com.example.famousmovies.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

}