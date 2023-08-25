/* Developed by Manhal */

package com.manhal.movies.models

import androidx.compose.runtime.Immutable

@Immutable
data class Video(
  val id: String,
  val name: String,
  val site: String,
  val key: String,
  val size: Int,
  val type: String
)
