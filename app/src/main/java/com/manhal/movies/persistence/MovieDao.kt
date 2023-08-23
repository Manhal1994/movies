package com.manhal.movies.persistence
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manhal.movies.models.entities.Movie

@Dao
interface MovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMovieList(movies: List<Movie>)

  @Update
  suspend fun updateMovie(movie: Movie)

  @Query("SELECT * FROM MOVIE WHERE id = :id_")
  suspend fun getMovie(id_: Long): Movie

  @Query("SELECT * FROM Movie WHERE page = :page_")
  suspend fun getMovieList(page_: Int): List<Movie>

  @Query("SELECT * FROM Movie WHERE LOWER(title) Like '%' || LOWER(:title_) || '%'")
  suspend fun searchByTitle(title_: String): List<Movie>

  @Query("Select * FROM Movie WHERE LOWER(title) Like '%' || LOWER(:title_) || '%' AND id in (select movieId from MovieGenre WHERE genreId in (:genreIds_))")
  suspend fun searchByTitleAndGenre(title_:String,genreIds_:List<String>): List<Movie>
}
