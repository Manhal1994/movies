package com.manhal.movies.ui.movie.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.manhal.movies.R
import com.manhal.movies.ui.movie.MovieDetailViewModel

@Composable
fun MovieHomepage(viewModel: MovieDetailViewModel) {
    val movieDetail by viewModel.movieDetailFlow.collectAsState(initial = null)
    if (movieDetail != null) {
        val uriHandler = LocalUriHandler.current
        Column {

            Spacer(modifier = Modifier.height(23.dp))

            Text(
                text = stringResource(R.string.homepage),
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
                    .clickable {
                        uriHandler.openUri(movieDetail!!.homepage)


                    }) {

                Text(
                    text = movieDetail!!.homepage,
                    color = Color.White,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )

                )
                Spacer(modifier = Modifier.width(4.dp))


            }
        }

    }
}