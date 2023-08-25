/* Developed by Manhal */

package com.manhal.movies.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manhal.movies.repository.MovieDetailRepository
import com.manhal.movies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieRepository: MovieRepository,
  private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

  private val movieIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

  val videoListFlow = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadVideoList(it)
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  val movieFlow = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadMovieById(it)
  }

  val movieDetailFlow = movieIdSharedFlow.flatMapLatest {
    movieDetailRepository.loadMovieDetail(it, {}, {})
  }

  val keywordListFlow = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadKeywordList(it)
  }

  val revenue = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadMovieRevenue(it)
  }

  val reviewListFlow = movieIdSharedFlow.flatMapLatest {
    movieRepository.loadReviewsList(it)
  }

  init {
    Timber.d("Injection MovieDetailViewModel")
  }

  fun fetchMovieDetailsById(id: Long) = movieIdSharedFlow.tryEmit(id)
}
