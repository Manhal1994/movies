package com.manhal.movies.network.service

import com.manhal.movies.models.entities.MovieDetail
import com.manhal.movies.models.network.VideoListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {
    @GET("/3/movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") id: Long): ApiResponse<MovieDetail>
}