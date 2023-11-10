package com.example.famousmovies.data

interface AppContainer {
    val dataRepository:DataRepository
}

class MovieAppContainer:AppContainer{
    override val dataRepository: DataRepository=MovieDataRepository()
}