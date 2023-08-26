/* Developed by Manhal */

package com.manhal.movies.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.manhal.movies.models.entities.Genre
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.models.network.NetworkState
import com.manhal.movies.repository.DiscoverRepository
import com.manhal.movies.repository.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  val imageLoader: ImageLoader,
  private val discoverRepository: DiscoverRepository,
  private val genreRepository: GenreRepository,

) : ViewModel() {

  private val _movieLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val movieLoadingState: State<NetworkState> get() = _movieLoadingState

  val searchText: MutableStateFlow<String> = MutableStateFlow<String>("")

  val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(mutableListOf())
  val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)

  val searchResult: MutableStateFlow<List<Movie>> = MutableStateFlow(mutableListOf())

  private val _genres: MutableStateFlow<List<Genre>> = MutableStateFlow(mutableListOf<Genre>())
  val genres: MutableStateFlow<List<Genre>> = _genres

  val selectedGenresStateFlow: MutableStateFlow<List<Int>> = MutableStateFlow(mutableListOf())

  val selectedGenres = arrayListOf<Int>()

  val searchActive: MutableStateFlow<Boolean> = MutableStateFlow(false)

  val query = mutableStateOf("")

  private val newMovieFlow = moviePageStateFlow.flatMapLatest {
    _movieLoadingState.value = NetworkState.LOADING
    discoverRepository.loadMovies(
      page = it,
      success = {
        _movieLoadingState.value = NetworkState.SUCCESS
      },
      error = {

        _movieLoadingState.value = NetworkState.ERROR
      }
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  init {
    viewModelScope.launch(Dispatchers.IO) {
      newMovieFlow.collectLatest {

        movies.value = it
      }
    }
    viewModelScope.launch(Dispatchers.IO) {

      genreRepository.loadGenreList(
        success = {}, error = {}
      ).collectLatest {
        _genres.value = it
      }
    }
  }

  fun toggleGenreFilter(id: Int) {
    if (selectedGenres.contains(id)) {
      selectedGenres.remove(id)
    } else {
      selectedGenres.add(id)
    }
    selectedGenresStateFlow.value = selectedGenres.toList()
  }

  fun searchMovies() {
    viewModelScope.launch(Dispatchers.IO) {
      searchActive.value = true
      _movieLoadingState.value = NetworkState.LOADING
      if (selectedGenres.isEmpty()) {
        discoverRepository.searchMovies(
          query.value.trim(),
          success = { _movieLoadingState.value = NetworkState.SUCCESS },
          error = { _movieLoadingState.value = NetworkState.ERROR }
        ).collectLatest {
          searchResult.value = it
        }
      } else {
        discoverRepository.searchMoviesWithGenre(
          query.value.trim(),
          selectedGenres,
          success = { _movieLoadingState.value = NetworkState.SUCCESS },
          error = { _movieLoadingState.value = NetworkState.ERROR }
        ).collectLatest {
          searchResult.value = it
        }
      }
    }
  }

  fun fetchNextMoviePage() {
    if (movieLoadingState.value != NetworkState.LOADING) {
      moviePageStateFlow.value++
    }
  }
}
