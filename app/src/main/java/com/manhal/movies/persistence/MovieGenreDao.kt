/* Developed by Manhal */

package com.manhal.movies.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.manhal.movies.models.entities.MovieGenre

@Dao
interface MovieGenreDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertGenreMovieList(movieGenreDaoList: List<MovieGenre>)
}
