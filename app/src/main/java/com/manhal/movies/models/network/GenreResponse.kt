package com.manhal.movies.models.network

import com.manhal.movies.models.entities.Genre

data class GenreResponse(
    val genres: List<Genre>
)