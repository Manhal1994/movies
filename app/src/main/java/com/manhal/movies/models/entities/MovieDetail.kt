/* Developed by Manhal */

package com.manhal.movies.models.entities

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import com.manhal.movies.models.SpokenLanguage

@Immutable
@Entity(primaryKeys = [("id")])
data class MovieDetail(
  var id: Int,
  val genres: List<Genre>,
  val homepage: String,
  val budget: Int,
  val revenue: Int,
  val runtime: Int,
  val spoken_languages: List<SpokenLanguage>,
  val status: String,
  val title: String,
)
