/* Developed by Manhal */

package com.manhal.movies.models.entities
import androidx.compose.runtime.Immutable
import androidx.room.Entity

@Immutable
@Entity(primaryKeys = [("id")])
data class Genre(
  val id: Int,
  val name: String
)
