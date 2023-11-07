package com.example.famousmovies

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.famousmovies.screens.MainScreen

enum class Screen{
    MAINSCREEN,DETAILSCREEN
}
@Composable
fun FamousMoviesApp(
    modifier:Modifier=Modifier
) {
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MAINSCREEN.name
    ){
        composable(Screen.MAINSCREEN.name) {
            MainScreen()
        }
        composable(Screen.DETAILSCREEN.name) {

        }
    }
}