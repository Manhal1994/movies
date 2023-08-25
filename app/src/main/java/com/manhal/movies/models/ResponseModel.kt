/* Developed by Manhal */

package com.manhal.movies.models

import androidx.compose.runtime.Immutable

@Immutable
data class ResponseModel(
  val page: Int,
  val results: Any,
  val total_results: Int,
  val total_pages: Int
)
