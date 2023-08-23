
package com.manhal.movies.ui.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.google.accompanist.insets.statusBarsPadding
import com.skydoves.landscapist.palette.BitmapPalette
import com.manhal.movies.extensions.paging
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.models.network.NetworkState
import com.manhal.movies.models.network.onLoading
import com.manhal.movies.network.Api
import com.manhal.movies.network.compose.NetworkImage
import com.manhal.movies.ui.main.MainViewModel


@Composable
fun MovieScreen(
    viewModel: MainViewModel,
    selectPoster: ( Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val networkState: NetworkState by viewModel.movieLoadingState
    val movies by viewModel.movies

    val genres = viewModel.genres.collectAsState()
    val selectedGenre = viewModel.selectedGenresStateFlow.collectAsState()
    val genresVisibility = viewModel.genresVisibility.collectAsState()


    Column {
        val textState = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(6.dp))


        ) {
            TextField(
                value = textState.value,
                onValueChange = {
                    textState.value = it
                    viewModel.searchMovies(textState.value)

                },
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {

                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),


                )
        }

        if(genresVisibility.value) {
            LazyRow(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                items(count = genres.value.size) { index ->
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 0.9.dp,
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Black,
                            )
                            .then(
                                if (selectedGenre.value.contains(genres.value[index].id)) Modifier.background(
                                    color = MaterialTheme.colors.primary
                                ) else Modifier.background(color = Color.White)
                            )
                            .padding(horizontal = 8.dp)
                            .clickable {
                                viewModel.toggleGenreFilter(genres.value[index].id)
                                viewModel.searchMovies(textState.value)
                            },
                        contentAlignment = Alignment.Center
                    ) {


                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight(),
                            text = genres.value[index].name,
                            color = (if (selectedGenre.value.contains(genres.value[index].id)) Color.White else Color.Black),
                            textAlign = TextAlign.Center,
                        )


                    }
                    Spacer(modifier = Modifier.width(8.dp))

                }
            }
        }


        LazyColumn(
            modifier = modifier
                .statusBarsPadding()
                .background(MaterialTheme.colors.background)
        ) {

            paging(
                items = movies,
                currentIndexFlow = viewModel.moviePageStateFlow,
                fetch = { viewModel.fetchNextMoviePage() }
            ) {

                Box(modifier = Modifier.padding(8.dp)) {
                    MoviePoster(
                        movie = it,
                        selectPoster = selectPoster
                    )
                }
            }
        }
    }

    networkState.onLoading {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun MoviePoster(
    movie: Movie,
    selectPoster: ( Long) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(modifier = modifier.clickable {
        selectPoster( movie.id)
    }) {
        Row() {
            var palette by remember { mutableStateOf<Palette?>(null) }
            NetworkImage(
                networkUrl = Api.getPosterPath(movie.poster_path),
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp),
                bitmapPalette = BitmapPalette {
                    palette = it
                }
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

