package com.example.famousmovies.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.famousmovies.R

@Composable
fun MainScreen(
    modifier:Modifier=Modifier
) {

}

@Preview
@Composable
fun MovieItem(
    modifier: Modifier=Modifier
) {
    var expanded=remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
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
                    imageVector = if(!expanded.value)   Icons.Default.KeyboardArrowDown
                                  else Icons.Default.KeyboardArrowUp,
                    contentDescription = if(!expanded.value) "Show More" else "Show less",
                    modifier = modifier.clickable {
                        expanded.value=!expanded.value
                    }
                )
                Text(
                    text = "The Shawshank Redemption",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(visible = expanded.value) {
                Image(
                    painter = painterResource(id = R.drawable.samplemovieimage),
                    contentDescription = "The Shawshank Redemption thumbnail")
            }
        }

    }
}