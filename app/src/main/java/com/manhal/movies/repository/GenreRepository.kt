package com.manhal.movies.repository
import androidx.annotation.WorkerThread
import com.manhal.movies.models.entities.Genre
import com.manhal.movies.network.service.GenreService
import com.manhal.movies.persistence.GenreDao
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion

class GenreRepository constructor(
    private val genreService: GenreService,
    private val genreDao: GenreDao
):Repository {
    @WorkerThread
    fun loadGenreList(success: () -> Unit, error: () -> Unit) = flow<List<Genre>> {
        val genres = genreDao.getGenreList()
        if (genres.isEmpty()) {
            val response = genreService.fetchGenres()
            response.suspendOnSuccess {
                genreDao.insertGenreList(this.data.genres)
                emit(this.data.genres ?: listOf())

            }.onError {
                error()
            }
        } else {
            emit(genres ?: listOf())
        }
    }.onCompletion { success() }.flowOn(Dispatchers.IO)
}