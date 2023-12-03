package com.example.famousmovies.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.famousmovies.model.MovieItem
import com.example.famousmovies.viewModel.MoviesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier=Modifier,
    movie: MovieItem,
    onBackPressed:()->Unit={}
) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = movie.title,
                    modifier=modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        modifier = modifier.clickable {
                            onBackPressed()
                        }
                    )
                }
            )
        },

        ) {it->
        Column(
            modifier=modifier.fillMaxSize(),
            //horizontalAlignment = Alignment.CenterHorizontally
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = movie.big_image,
                contentDescription = movie.title,
                modifier= modifier
                    .size(500.dp)
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(
                        bottom = 20.dp
                    )
            )
            var annotatedString=buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Rank: ")
                }
                append("${movie.rank}")
            }
            Text(
                annotatedString,
                modifier=modifier.padding(
                    start = 10.dp
                )
            )
            annotatedString=buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Description: ")
                }
                append(movie.description)
            }
            Text(
                annotatedString,
                modifier=modifier.padding(
                    start = 10.dp
                )
            )
            annotatedString=buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Genre: ")
                }
                append("${movie.genre}")
            }
            Text(
                annotatedString,
                modifier=modifier.padding(
                    start = 10.dp
                )
            )
            annotatedString=buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Rating: ")
                }
                append(movie.rating)
            }
            Text(
                annotatedString,
                modifier=modifier.padding(
                    start = 10.dp
                )
            )
            annotatedString=buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Year: ")
                }
                append("${movie.year}")
            }
            Text(
                annotatedString,
                modifier=modifier.padding(
                    start = 10.dp
                )
            )
        }
    }
}