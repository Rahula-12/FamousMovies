package com.example.famousmovies.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.famousmovies.model.MovieItem
import com.example.famousmovies.viewModel.MoviesState

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen(
    modifier:Modifier=Modifier,
    moviesState: State<MoviesState> = mutableStateOf(MoviesState()),
    movieClicked:(Int)->Unit={},
    movieSelected:(Int)->Unit={},
    selectedCheck:(Int)->Boolean={
                                 true
    },
    movieDetail:()->Unit={}
) {
        val movieList=moviesState.value.moviesList
        Column{
            TopAppBar(title = {
                Text(
                    text = "Top 100 Movies",
                    modifier=modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
                modifier=modifier.padding(
                    top=0.dp
                ))
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(movieList) {
                    MovieItem(
                        movieItem = it,
                        selectedCheck = selectedCheck,
                        onClicked = {
                            movieClicked(it.rank - 1)
                            movieDetail()
                            //movieSelected(it.rank - 1)
                        },
                        movieSelected = { index ->
                            movieSelected(index)
                        }
                    )
                }
            }
        }
}

@Composable
fun MovieItem(
    modifier: Modifier=Modifier,
    movieItem: MovieItem,
    onClicked:()->Unit={},
    selectedCheck:(Int)->Boolean,
    movieSelected:(Int)->Unit={},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClicked()
            },
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation=5.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                Icon(
                    imageVector = if(!selectedCheck(movieItem.rank-1))   Icons.Default.KeyboardArrowDown
                                  else Icons.Default.KeyboardArrowUp,
                    contentDescription = if(!selectedCheck(movieItem.rank-1)) "Show More" else "Show less",
                    modifier = modifier.clickable {
//                        Log.d("expanded","$expanded")
                        movieSelected(movieItem.rank-1)
                        //expanded=selectedCheck(movieItem.rank-1)
                        //Log.d("expanded2","${selectedCheck(movieItem.rank-1)}")
                    }
                )
                Text(
                    text = movieItem.title,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(visible = selectedCheck(movieItem.rank-1)) {
                //Log.d("expanded3","${selectedCheck(movieItem.rank-1)}")
                AsyncImage(
                    model = movieItem.big_image,
                    contentDescription = movieItem.title,
                    modifier= modifier
                        .size(
                            200.dp
                        )
                        .padding(
                            bottom = 10.dp
                        )
                )
            }
        }

    }
}