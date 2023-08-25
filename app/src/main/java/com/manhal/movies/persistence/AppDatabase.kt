/* Developed by Manhal */

package com.manhal.movies.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manhal.movies.models.entities.Genre
import com.manhal.movies.models.entities.Movie
import com.manhal.movies.models.entities.MovieDetail
import com.manhal.movies.models.entities.MovieGenre
import com.manhal.movies.persistence.converters.GenreConverter
import com.manhal.movies.persistence.converters.IntListConverter
import com.manhal.movies.persistence.converters.KeywordListConverter
import com.manhal.movies.persistence.converters.ReviewListConverter
import com.manhal.movies.persistence.converters.SpokenLanguageListConverter
import com.manhal.movies.persistence.converters.StringListConverter
import com.manhal.movies.persistence.converters.VideoListConverter

@Database(
  entities = [(Movie::class), (Genre::class), (MovieGenre::class), (MovieDetail::class)],
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
  abstract fun movieGenreDao(): MovieGenreDao
  abstract fun movieDetailDao(): MovieDetailDao
}
