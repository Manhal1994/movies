
package com.manhal.movies.models

import androidx.compose.runtime.Immutable

@Immutable
data class Review(
  val id: String,
  val author: String,
  val content: String,
  val url: String
)
