/* Developed by Manhal */

package com.manhal.movies.di

import com.manhal.movies.network.service.GenreService
import com.manhal.movies.network.service.MovieDetailService
import com.manhal.movies.network.service.MovieService
import com.manhal.movies.network.service.TheDiscoverService
import com.manhal.movies.persistence.GenreDao
import com.manhal.movies.persistence.MovieDao
import com.manhal.movies.persistence.MovieDetailDao
import com.manhal.movies.persistence.MovieGenreDao
import com.manhal.movies.repository.DiscoverRepository
import com.manhal.movies.repository.GenreRepository
import com.manhal.movies.repository.MovieDetailRepository
import com.manhal.movies.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideDiscoverRepository(
    discoverService: TheDiscoverService,
    movieDao: MovieDao,
    movieGenreDao: MovieGenreDao,
  ): DiscoverRepository {
    return DiscoverRepository(discoverService, movieDao, movieGenreDao)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
    movieService: MovieService,
    movieDao: MovieDao
  ): MovieRepository {
    return MovieRepository(movieService, movieDao)
  }

  @Provides
  @ViewModelScoped
  fun provideGenreRepository(
    genreService: GenreService,
    genreDao: GenreDao
  ): GenreRepository {
    return GenreRepository(genreService, genreDao)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieDetailRepository(
    movieDetailService: MovieDetailService,
    movieDetailDao: MovieDetailDao
  ): MovieDetailRepository {
    return MovieDetailRepository(movieDetailService, movieDetailDao)
  }
}
