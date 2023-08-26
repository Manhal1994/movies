/* Developed by Manhal */

package com.manhal.movies.repository

import android.annotation.SuppressLint
import androidx.annotation.WorkerThread
import com.manhal.movies.models.entities.MovieGenre
import com.manhal.movies.network.service.TheDiscoverService
import com.manhal.movies.persistence.MovieDao
import com.manhal.movies.persistence.MovieGenreDao
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber

class DiscoverRepository constructor(
  private val discoverService: TheDiscoverService,
  private val movieDao: MovieDao,
  private val movieGenreDao: MovieGenreDao,
) : Repository {

  init {
    Timber.d("Injection DiscoverRepository")
  }

  @WorkerThread
  fun loadMovies(page: Int, success: () -> Unit, error: () -> Unit) = flow {
    var movies = movieDao.getMovieList(page)
    val movieGenres = arrayListOf<MovieGenre>()

    if (movies.isEmpty()) {
      val response = discoverService.fetchDiscoverMovie(page)
      response.suspendOnSuccess {
        movies = data.results
        movies.forEach {
          movieGenres.clear()
          it.page = page
          for (genreId in it.genre_ids) {
            movieGenres.add(MovieGenre(genreId.toLong(), it.id))
          }
          movieGenreDao.insertGenreMovieList(movieGenres.toList())
        }
        movieDao.insertMovieList(movies)
        emit(movies)
      }.onError {
        error()
      }
        .onException { error() }
    } else {

      emit(movies)
    }
  }.onCompletion { success() }
    .flowOn(Dispatchers.IO)

  @SuppressLint("SuspiciousIndentation")
  @WorkerThread
  fun searchMovies(title: String, success: () -> Unit, error: () -> Unit) = flow {
    val movies = movieDao.searchByTitle(title)
    emit(movies)
  }.onCompletion { success() }.flowOn(Dispatchers.IO)

  @SuppressLint("SuspiciousIndentation")
  @WorkerThread
  fun searchMoviesWithGenre(title: String, genresIds: List<Int>, success: () -> Unit, error: () -> Unit) = flow {
    val ids = arrayListOf<String>()
    for (it in genresIds) {
      ids.add(it.toString())
    }
    val movies = movieDao.searchByTitleAndGenre(title, ids)
    emit(movies)
  }.onCompletion { success() }.flowOn(Dispatchers.IO)
}
