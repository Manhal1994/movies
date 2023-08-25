/* Developed by Manhal */

package com.manhal.movies.mapper

import com.manhal.movies.models.network.MovieErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<MovieErrorResponse> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): MovieErrorResponse {
    return MovieErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
