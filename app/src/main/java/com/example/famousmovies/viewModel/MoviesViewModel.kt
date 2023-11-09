package com.example.famousmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.famousmovies.data.Movie
import com.example.famousmovies.data.MoviesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel :ViewModel() {
    private val _movieState:MutableStateFlow<MoviesState>
    = MutableStateFlow(MoviesState())
    val moviesState:StateFlow<MoviesState> = _movieState
    init {
        viewModelScope.launch {
            _movieState.update {
                it.copy(
                    list=MoviesApi.retrofitService.getTopMovies().await()
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
}

data class MoviesState(
    val list:List<Movie> = listOf(),
    val selected:Int=-1
)