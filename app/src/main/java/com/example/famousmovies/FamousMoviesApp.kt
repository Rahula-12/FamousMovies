package com.example.famousmovies

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.famousmovies.screens.DetailScreen
import com.example.famousmovies.screens.MainScreen
import com.example.famousmovies.viewModel.MoviesViewModel

enum class Screen{
    MAINSCREEN,DETAILSCREEN
}
@Composable
fun FamousMoviesApp(
    viewModel: MoviesViewModel,
    modifier:Modifier=Modifier
) {
    val moviesState=viewModel.moviesState.collectAsState()
    Log.d("Movies",moviesState.value.list.size.toString())
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MAINSCREEN.name
    ){
        composable(Screen.MAINSCREEN.name) {
            MainScreen(
                onCardClicked = {
                    navController.navigate(Screen.DETAILSCREEN.name)
//                    Log.d("Test","I was pressed")
                },
                viewModel = viewModel
            )
        }
        composable(Screen.DETAILSCREEN.name) {
            DetailScreen(
                viewModel=viewModel,
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}