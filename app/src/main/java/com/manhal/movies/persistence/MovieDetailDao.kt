/* Developed by Manhal */

package com.manhal.movies.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manhal.movies.models.entities.MovieDetail

@Dao
interface MovieDetailDao {
  @Query("SELECT * FROM MovieDetail WHERE id = :id_")
  suspend fun getMovieDetail(id_: Long): MovieDetail?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovieDetail(movieDetail_: MovieDetail)
}
