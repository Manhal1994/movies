package com.manhal.movies.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manhal.movies.models.entities.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre")
    suspend fun getGenreList(): List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreList(genres: List<Genre>)
}