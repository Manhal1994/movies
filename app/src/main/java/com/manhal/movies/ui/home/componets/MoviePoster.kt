package com.manhal.movies.ui.home.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.network.Api
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MoviePoster(
    movie: Movie,
    selectPoster: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(modifier = modifier.clickable {
        selectPoster(movie.id)
    }) {
        Row() {
            val context = LocalContext.current
            val imageRequest = ImageRequest.Builder(context)
                .data(Api.getPosterPath(movie.poster_path))
                .memoryCacheKey(Api.getPosterPath(movie.poster_path))
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build()
            CoilImage(
                imageRequest = imageRequest,
                modifier = Modifier
                    .width(100.dp)
                    .height(125.dp),
                contentDescription = null,
                imageLoader = { context.imageLoader }


            )

            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp


                )
                Spacer(modifier = Modifier.height(8.dp))
                movie.release_date?.split("-")?.get(0)?.let {
                    Text(
                        text = it, fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

            }

        }
    }

}