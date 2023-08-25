/* Developed by Manhal */

package com.manhal.movies.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Entity

@Immutable
@Entity(primaryKeys = [("genreId"), ("movieId")])
data class MovieGenre(
  var genreId: Long,
  var movieId: Long,

)
