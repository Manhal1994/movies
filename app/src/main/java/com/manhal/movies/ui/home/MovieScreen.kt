/* Developed by Manhal */

package com.manhal.movies.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.manhal.movies.models.network.NetworkState
import com.manhal.movies.models.network.onLoading
import com.manhal.movies.ui.home.componets.GenreList
import com.manhal.movies.ui.home.componets.MovieList
import com.manhal.movies.ui.home.componets.MoviePoster
import com.manhal.movies.ui.home.componets.MovieSearch
import com.manhal.movies.ui.main.MainViewModel

@Composable
fun MovieScreen(
  viewModel: MainViewModel,
  selectPoster: (Long) -> Unit,
  modifier: Modifier = Modifier
) {
  val networkState: NetworkState by viewModel.movieLoadingState
  val movies by viewModel.movies

  val genres = viewModel.genres.collectAsState()
  val selectedGenre = viewModel.selectedGenresStateFlow.collectAsState()
  val genresVisibility = viewModel.genresVisibility.collectAsState()
  val searchResult = viewModel.searchResult.collectAsState()
  val searchActive = viewModel.searchActive.collectAsState()

  Column {
    val textState = remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(16.dp))
    MovieSearch(textState, viewModel)

    if (genresVisibility.value) {
      GenreList(genres, selectedGenre, viewModel, textState)
    }

    if (!searchActive.value) {
      MovieList(modifier = modifier, movies, viewModel, selectPoster)
    } else {
      LazyColumn(
        modifier = modifier
          .statusBarsPadding()
          .background(MaterialTheme.colors.background)
      ) {
        itemsIndexed(searchResult.value) { _, item ->
          MoviePoster(movie = item, selectPoster = selectPoster)
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
