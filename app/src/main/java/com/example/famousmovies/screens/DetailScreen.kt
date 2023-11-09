package com.example.famousmovies.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.famousmovies.viewModel.MoviesViewModel

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier=Modifier,
    viewModel: MoviesViewModel= MoviesViewModel()
) {
    val moviesState=viewModel.moviesState.collectAsState()
    val movie=moviesState.value.list[moviesState.value.selected]
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = movie.title,
                    modifier=modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            })
        }
    ) {it->
         Column(
             modifier=modifier.fillMaxSize(),
             //horizontalAlignment = Alignment.CenterHorizontally
         ) {
             AsyncImage(
                 model = movie.big_image,
                 contentDescription = movie.title,
                 modifier=modifier.size(200.dp).align(
                     Alignment.CenterHorizontally
                 )
             )
             Text(text = "Rank:${movie.rank}")
             Text(text = "Description:${movie.description}")
             Text(text = "Genre:${movie.genre}")
             Text(text = "Rating:${movie.rating}")
             Text(text = "Year:${movie.year}")
         }
    }
}