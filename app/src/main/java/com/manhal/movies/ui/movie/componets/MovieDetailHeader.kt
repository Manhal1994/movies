package com.manhal.movies.ui.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.network.Api
import com.manhal.movies.network.compose.NetworkImage
import com.manhal.movies.ui.custom.RatingBar
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
 fun MovieDetailHeader(
    viewModel: MovieDetailViewModel
) {
    val movie: Movie? by viewModel.movieFlow.collectAsState(initial = null)

    Column {

        var palette by remember { mutableStateOf<Palette?>(null) }
        NetworkImage(
            networkUrl = Api.getBackdropPath(movie?.backdrop_path),
            circularReveal = CircularReveal(duration = 300),
            shimmerParams = null,
            bitmapPalette = BitmapPalette {
                palette = it
            },
            modifier = Modifier
                .height(280.dp)

        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = movie?.title ?: "",
            style = MaterialTheme.typography.h5,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Release Date: ${movie?.release_date}",
            style = MaterialTheme.typography.body1,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        RatingBar(
            rating = (movie?.vote_average ?: 0f) / 2f,
            color = Color(palette?.vibrantSwatch?.rgb ?: 0),
            modifier = Modifier
                .height(15.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}