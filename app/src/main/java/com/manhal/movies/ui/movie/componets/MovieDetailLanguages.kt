package com.manhal.movies.ui.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.movies.R

@Composable
 fun MovieDetailLanguages(
    viewModel: MovieDetailViewModel
) {
    val movieDetail by viewModel.movieDetailFlow.collectAsState(initial = null)
    if (movieDetail != null) {
        Column {

            Spacer(modifier = Modifier.height(23.dp))

            Text(
                text = stringResource(R.string.languages),
                style = MaterialTheme.typography.h6,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Box(

                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                Row() {

                    movieDetail!!.spoken_languages.forEach {
                        Text(

                            text = "${it.english_name} ",
                            color = Color.White,

                            )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                }
            }

        }
    }

}