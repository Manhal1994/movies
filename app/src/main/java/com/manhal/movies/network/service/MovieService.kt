/* Developed by Manhal */

package com.manhal.movies.network.service

import com.manhal.movies.models.network.KeywordListResponse
import com.manhal.movies.models.network.MovieDetailsResponse
import com.manhal.movies.models.network.ReviewListResponse
import com.manhal.movies.models.network.VideoListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
  /**
   * [Movie Keywords](https://developers.themoviedb.org/3/movies/get-movie-keywords)
   *
   * Get the keywords that have been added to a movie.
   *
   * @param [id] Specify the id of movie id.
   *
   * @return [KeywordListResponse] response
   */
  @GET("/3/movie/{movie_id}/keywords")
  suspend fun fetchKeywords(@Path("movie_id") id: Long): ApiResponse<KeywordListResponse>

  /**
   * [Movie Videos](https://developers.themoviedb.org/3/movies/get-movie-videos)
   *
   * Get the videos that have been added to a movie.
   *
   * @param [id] Specify the id of movie id.
   *
   * @return [VideoListResponse] response
   */
  @GET("/3/movie/{movie_id}/videos")
  suspend fun fetchVideos(@Path("movie_id") id: Long): ApiResponse<VideoListResponse>

  /**
   * [Movie Reviews](https://developers.themoviedb.org/3/movies/get-movie-reviews)
   *
   * Get the user reviews for a movie.
   *
   * @param [id] Specify the id of movie id.
   *
   * @return [ReviewListResponse] response
   */
  @GET("/3/movie/{movie_id}/reviews")
  suspend fun fetchReviews(@Path("movie_id") id: Long): ApiResponse<ReviewListResponse>

  @GET("/3/movie/{movie_id}")
  suspend fun fetchMovieDetail(@Path("movie_id") id: Long): ApiResponse<MovieDetailsResponse>
}
