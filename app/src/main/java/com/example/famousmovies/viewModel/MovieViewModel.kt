package com.example.famousmovies.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.famousmovies.model.MovieItem
import com.example.famousmovies.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MoviesState(
    val moviesList:List<MovieItem> = emptyList(),
    val selectedMovies:Set<Int> = setOf(),
    val clicked:Int=-1
)

class MovieViewModel
@Inject constructor(private val movieRepository: MovieRepository)
    :ViewModel() {
    private val _movies:MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState())
    val movies:StateFlow<MoviesState> = _movies
    init {
        viewModelScope.launch {
            _movies.update {
                it.copy(
                    moviesList = movieRepository.getMovies()
                )
            }
            Log.d("Size2",_movies.value.moviesList.size.toString())
        }
        Log.d("Size",_movies.value.moviesList.size.toString())
    }
    fun selectOrDeselectMovie(index:Int) {
        val copy:MutableSet<Int> = mutableSetOf()
        copy.addAll(_movies.value.selectedMovies)
        if(copy.contains(index)) {
            copy.remove(index)
        }
        else {
            copy.add(index)
        }
        _movies.update {
            it.copy(
                selectedMovies = copy
            )
        }
    }
    fun movieClicked(index:Int) {
        _movies.update {
            it.copy(
                clicked=index
            )
        }
    }

    fun selectedCheck(index:Int):Boolean {
        return _movies.value.selectedMovies.contains(index)
    }
}