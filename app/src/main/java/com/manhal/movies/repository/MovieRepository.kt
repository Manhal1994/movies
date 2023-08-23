package com.manhal.movies.repository

import androidx.annotation.WorkerThread
import com.manhal.movies.models.Keyword
import com.manhal.movies.models.Review
import com.manhal.movies.models.SpokenLanguage
import com.manhal.movies.models.Video
import com.manhal.movies.network.service.MovieService
import com.manhal.movies.persistence.MovieDao
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MovieRepository constructor(
  private val movieService: MovieService,
  private val movieDao: MovieDao
) : Repository {

  init {
    Timber.d("Injection MovieRepository")
  }

  @WorkerThread
  fun loadKeywordList(id: Long) = flow<List<Keyword>> {
    val movie = movieDao.getMovie(id)
    var keywords = movie.keywords
    if (keywords.isNullOrEmpty()) {
      val response = movieService.fetchKeywords(id)
      response.suspendOnSuccess {
        keywords = data.keywords
        movie.keywords = keywords
        emit(keywords ?: listOf())
        movieDao.updateMovie(movie)
      }
    } else {
      emit(keywords ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadVideoList(id: Long) = flow<List<Video>> {
    val movie = movieDao.getMovie(id)
    var videos = movie.videos
    if (videos.isNullOrEmpty()) {
      movieService.fetchVideos(id)
        .suspendOnSuccess {
          videos = data.results
          movie.videos = videos
          movieDao.updateMovie(movie)
          emit(videos ?: listOf())
        }
    } else {
      emit(videos ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadReviewsList(id: Long) = flow<List<Review>> {
    val movie = movieDao.getMovie(id)
    var reviews = movie.reviews
    if (reviews.isNullOrEmpty()) {
      movieService.fetchReviews(id)
        .suspendOnSuccess {
          reviews = data.results
          movie.reviews = reviews
          movieDao.updateMovie(movie)
          emit(reviews ?: listOf())
        }
    } else {
      emit(reviews ?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadMovieSpokenLanguages(id: Long) = flow<List<SpokenLanguage>> {
    val movie = movieDao.getMovie(id)
    var spokenLanguages = movie.spokenLanguages
    if (spokenLanguages.isNullOrEmpty()) {
      movieService.fetchMovieDetail(id)
        .suspendOnSuccess {
          spokenLanguages = data.spoken_languages
          movie.spokenLanguages = spokenLanguages
          movieDao.updateMovie(movie)
          emit(spokenLanguages?:listOf())
        }
    } else {
      emit(spokenLanguages?: listOf())
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadMovieRevenue(id: Long) = flow<Int?> {
    val movie = movieDao.getMovie(id)
    var revenue = movie.revenue
    if (revenue==null) {
      movieService.fetchMovieDetail(id)
        .suspendOnSuccess {
          revenue = data.revenue
          movie.revenue = revenue
          movieDao.updateMovie(movie)
          emit(revenue!!)
        }
    } else {
      emit(null)
    }
  }.flowOn(Dispatchers.IO)




  @WorkerThread
  fun loadMovieById(id: Long) = flow {
    val movie = movieDao.getMovie(id)
    emit(movie)
  }.flowOn(Dispatchers.IO)
}
