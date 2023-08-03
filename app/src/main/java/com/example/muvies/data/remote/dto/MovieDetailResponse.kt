package com.example.muvies.data.remote.dto

data class MovieDetailResponse(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = null,
    val id: Int? = 0,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val runtime: Int? = 0,
    val status: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = 0,
)
