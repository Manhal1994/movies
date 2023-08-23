package com.manhal.movies.models.network

import androidx.compose.runtime.Immutable
import com.manhal.movies.models.NetworkResponseModel
import com.manhal.movies.models.Review

@Immutable
class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
