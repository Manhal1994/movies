
package com.manhal.movies.di

import android.content.Context
import androidx.room.Room
import com.manhal.movies.persistence.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
    return Room
      .databaseBuilder(context, AppDatabase::class.java, "MovieCompose.db")
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
    return appDatabase.movieDao()
  }

  @Provides
  @Singleton
  fun provideGenreDao(appDatabase: AppDatabase): GenreDao {
    return appDatabase.genreDao()
  }

  @Provides
  @Singleton
  fun provideMovieGenreDao(appDatabase: AppDatabase): MovieGenreDao {
    return appDatabase.movieGenreDao()
  }

  @Provides
  @Singleton
  fun provideMovieDetailDao(appDatabase: AppDatabase): MovieDetailDao {
    return appDatabase.movieDetailDao()
  }



}
