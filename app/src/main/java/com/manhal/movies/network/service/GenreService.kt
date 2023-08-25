/* Developed by Manhal */

package com.manhal.movies.network.service

import com.manhal.movies.models.network.GenreResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface GenreService {
  @GET("/3/genre/movie/list")
  suspend fun fetchGenres(): ApiResponse<GenreResponse>
}
