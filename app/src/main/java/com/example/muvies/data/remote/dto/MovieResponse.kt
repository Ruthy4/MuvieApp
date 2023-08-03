package com.example.muvies.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val page: Int? = 0,
    val results: List<Movie>? = emptyList(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)
