package com.manhal.movies.ui.movie

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.ui.custom.AppBarWithArrow
import com.manhal.movies.ui.movie.componets.*
import com.manhal.movies.ui.theme.background



@Composable
fun MovieDetailScreen(
    posterId: Long,
    viewModel: MovieDetailViewModel,
    pressOnBack: () -> Unit
) {
    val movie: Movie? by viewModel.movieFlow.collectAsState(initial = null)

    LaunchedEffect(key1 = posterId) {
        viewModel.fetchMovieDetailsById(posterId)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(background)
            .fillMaxSize(),
    ) {

        AppBarWithArrow(movie?.title, pressOnBack)

        MovieDetailHeader(viewModel)

        MovieDetailVideos(viewModel)

        MovieDetailGenres(viewModel)

        MovieDetailOverview(viewModel)

        MovieHomepage(viewModel)

        MovieStatus(viewModel)

        MovieRunTime(viewModel)

        MovieBudget(viewModel)

        MovieRevenue(viewModel)

        MovieDetailLanguages(viewModel)

        MovieDetailReviews(viewModel)

        Spacer(modifier = Modifier.height(24.dp))
    }
}




























