package com.example.famousmovies.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.famousmovies.R
import com.example.famousmovies.viewModel.MoviesState
import com.example.famousmovies.viewModel.MoviesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier:Modifier=Modifier,
    onCardClicked:()->Unit={},
    viewModel:MoviesViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Top 100 Movies",
                    textAlign = TextAlign.Center,
                    modifier=modifier.fillMaxWidth()
                )
            }
            )
        }
    ) { it ->
        val moviesState=viewModel.moviesState.collectAsState()
        if (moviesState.value.list.isEmpty()) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        } else {
            LazyColumn {
                items(moviesState.value.list) {
                    MovieItem(
                        title=it.title,
                        imageUrl = it.big_image,
                        onMovieClicked = {
                            viewModel.updateSelected(it.rank-1)
                            onCardClicked()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MovieItem(
    modifier: Modifier=Modifier,
    onMovieClicked:()->Unit={
        Log.d("Test","I am pressed")
    },
    title:String="",
    imageUrl:String=""
) {
    val expanded=remember {
        mutableStateOf(false)
    }
    Card(
        onClick = {
            onMovieClicked()
                  } ,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp
            )
        ,
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation=2.5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor= Color.Transparent
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = if(!expanded.value)   Icons.Default.KeyboardArrowDown
                                  else Icons.Default.KeyboardArrowUp,
                    contentDescription = if(!expanded.value) "Show More" else "Show less",
                    modifier = Modifier.clickable {
                        expanded.value=!expanded.value
                    }
                )
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),
                    fontSize = TextUnit(25f, TextUnitType.Sp)
                )
            }
            AnimatedVisibility(visible = expanded.value) {
                AsyncImage(
                    model=imageUrl,
                    contentDescription = title,
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