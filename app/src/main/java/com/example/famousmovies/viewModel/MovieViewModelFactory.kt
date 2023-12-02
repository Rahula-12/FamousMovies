package com.example.famousmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.famousmovies.repository.MovieRepository
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(private val movieRepository: MovieRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }

}