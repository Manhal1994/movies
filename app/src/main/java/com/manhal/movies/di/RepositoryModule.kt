
package com.manhal.movies.di

import com.manhal.movies.network.service.*
import com.manhal.movies.persistence.*
import com.manhal.movies.repository.*
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
    return DiscoverRepository(discoverService, movieDao,movieGenreDao)
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


}
