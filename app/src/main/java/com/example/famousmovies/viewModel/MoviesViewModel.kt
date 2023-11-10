package com.example.famousmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.famousmovies.MovieApplication
import com.example.famousmovies.data.DataRepository
import com.example.famousmovies.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: DataRepository
) :ViewModel() {
    private val _movieState:MutableStateFlow<MoviesState>
    = MutableStateFlow(MoviesState())
    val moviesState:StateFlow<MoviesState> = _movieState
    init {
        viewModelScope.launch {
            _movieState.update {
                it.copy(
                    list=repository.retrofitService.getTopMovies().await()
                )
            }
        }
    }

    fun updateSelected(pos:Int) {
        _movieState.update {
            it.copy(
                selected = pos
            )
        }
    }

    companion object {
        val factory= viewModelFactory {
            initializer {
                val application=this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                MoviesViewModel(application.container.dataRepository)
            }
        }
    }
}

data class MoviesState(
    val list:List<Movie> = listOf(),
    val selected:Int=-1
)