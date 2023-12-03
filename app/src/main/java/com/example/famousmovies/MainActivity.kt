package com.example.famousmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.famousmovies.repository.MovieRepository
import com.example.famousmovies.ui.theme.FamousMoviesTheme
import com.example.famousmovies.viewModel.MovieViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MovieApplication).component.inject(this)
//        lifecycleScope.launch(Dispatchers.IO) {
//            val size=repository.getMovies().size
//            Log.d("Size",size.toString())
//        }
        setContent {
            FamousMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Log.d("Size",size.toString())
                      MovieApp(
                          moviesState = viewModel.movies.collectAsState(),
                          movieSelected = {
                              viewModel.selectOrDeselectMovie(it)
                          },
                          movieClicked = {
                              viewModel.movieClicked(it)
                          },
                          selectedCheck = {
                              viewModel.selectedCheck(it)
                          }
                      )
                }
            }
        }
    }
}