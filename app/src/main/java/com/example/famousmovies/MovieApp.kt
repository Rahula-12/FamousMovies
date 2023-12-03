package com.example.famousmovies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.famousmovies.screens.DetailScreen
import com.example.famousmovies.screens.MainScreen
import com.example.famousmovies.viewModel.MoviesState

enum class ScreenName{
    MAINSCREEN,
    DETAILSCREEN
}

@Composable
fun MovieApp(
    modifier:Modifier=Modifier,
    moviesState: State<MoviesState>,
    movieSelected:(Int)->Unit={},
    movieClicked:(Int)->Unit={},
    selectedCheck:(Int)->Boolean
) {
    val navController= rememberNavController()
    NavHost(navController = navController,
        startDestination = ScreenName.MAINSCREEN.name){
        composable(ScreenName.MAINSCREEN.name) {
            MainScreen(
                moviesState = moviesState,
                movieClicked = movieClicked,
                movieSelected = movieSelected,
                movieDetail = {
                    navController.navigate(ScreenName.DETAILSCREEN.name)
                },
                selectedCheck = selectedCheck
            )
        }
        composable(ScreenName.DETAILSCREEN.name){
            DetailScreen(
                movie = moviesState.value.moviesList[moviesState.value.clicked],
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}