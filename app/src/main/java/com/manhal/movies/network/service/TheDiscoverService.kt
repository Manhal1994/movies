/* Developed by Manhal */

package com.manhal.movies.network.service

import com.manhal.movies.models.network.DiscoverMovieResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDiscoverService {
  @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
  suspend fun fetchDiscoverMovie(@Query("page") page: Int): ApiResponse<DiscoverMovieResponse>
}
