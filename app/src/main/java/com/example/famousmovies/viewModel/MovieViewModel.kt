package com.example.famousmovies.viewModel

import androidx.lifecycle.ViewModel
import com.example.famousmovies.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel
@Inject constructor(private val movieRepository: MovieRepository)
    :ViewModel() {

}