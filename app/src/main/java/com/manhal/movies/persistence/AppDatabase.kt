
package com.manhal.movies.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manhal.movies.models.entities.*
import com.manhal.movies.persistence.converters.*

@Database(
  entities = [(Movie::class),(Genre::class),(MovieGenre::class),(MovieDetail::class)],
  version = 3, exportSchema = false
)
@TypeConverters(
  value = [
      (StringListConverter::class),
      (KeywordListConverter::class),
      (VideoListConverter::class),
      (ReviewListConverter::class),
      (SpokenLanguageListConverter::class),
      (IntListConverter::class),
      (GenreConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun genreDao(): GenreDao
  abstract fun movieGenreDao() : MovieGenreDao
  abstract fun movieDetailDao() : MovieDetailDao


}
