/* Developed by Manhal */

package com.manhal.movies.ui.home.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.statusBarsPadding
import com.manhal.movies.extensions.paging
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.ui.main.MainViewModel

@Composable
fun MovieList(modifier: Modifier, movies: List<Movie>, viewModel: MainViewModel, selectPoster: (Long) -> Unit) {
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
      MoviePoster(
        movie = it,
        selectPoster = selectPoster
      )
    }
  }
}
