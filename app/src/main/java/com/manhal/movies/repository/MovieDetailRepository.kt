package com.manhal.movies.repository

import androidx.annotation.WorkerThread
import com.manhal.movies.models.entities.MovieGenre
import com.manhal.movies.network.service.GenreService
import com.manhal.movies.network.service.MovieDetailService
import com.manhal.movies.persistence.MovieDetailDao
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class MovieDetailRepository constructor(
    private val movieDetailService:MovieDetailService,
    private val movieDetailDao: MovieDetailDao
):Repository {


    @WorkerThread
    fun loadMovieDetail(id: Long,success: () -> Unit, error: () -> Unit) = flow {
        val movieDetail = movieDetailDao.getMovieDetail(id)

        if (movieDetail==null) {
            val response = movieDetailService.fetchMovieDetail(id)
            response.suspendOnSuccess {
                movieDetailDao.insertMovieDetail(data)
                emit(data)
            }.onError {
                error()
            }
        } else {
            emit(movieDetail)
        }
    }.onCompletion { success() }.flowOn(Dispatchers.IO)
}