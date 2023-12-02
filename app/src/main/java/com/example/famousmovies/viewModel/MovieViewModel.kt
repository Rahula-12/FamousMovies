package com.example.famousmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.famousmovies.model.MovieItem
import com.example.famousmovies.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MoviesState(
    var moviesList:List<MovieItem> = emptyList(),
    val selectedMovies:MutableSet<Int> = mutableSetOf(),
    var clicked:Int=-1
)

class MovieViewModel
@Inject constructor(private val movieRepository: MovieRepository)
    :ViewModel() {
    private val _movies:MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState())
    val movies:StateFlow<MoviesState> = _movies

    init {
        viewModelScope.launch {
            _movies.value.moviesList=movieRepository.getMovies()
        }
    }

    fun selectOrDeselectMovie(index:Int) {
        if(_movies.value.selectedMovies.contains(index)) {
            _movies.value.selectedMovies.remove(index)
        }
        else {
            _movies.value.selectedMovies.add(index)
        }
    }
    fun movieClicked(index:Int) {
        _movies.value.clicked=index
    }
}