/* Developed by Manhal */

package com.manhal.movies.models.network

import androidx.compose.runtime.Immutable
import com.manhal.movies.models.NetworkResponseModel
import com.manhal.movies.models.entities.Movie

@Immutable
data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
