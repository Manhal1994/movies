
package com.manhal.movies.models.network

import androidx.compose.runtime.Immutable
import com.manhal.movies.models.NetworkResponseModel
import com.manhal.movies.models.Video

@Immutable
data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
