package com.example.muvies.util

import com.example.muvies.data.local.MovieData
import com.example.muvies.data.remote.dto.Genre
import com.example.muvies.data.remote.dto.Movie
import com.example.muvies.data.remote.dto.MovieDetailResponse
import com.example.muvies.data.remote.dto.MovieResponse

val sampleMovie = Movie(
    id = 0,
    adult = false,
    backdrop_path = "",
    original_language = "en",
    original_title = "Barbie",
    overview = "",
    popularity = 4587.093,
    poster_path = "",
    release_date = "2023-07-19",
    title = "Barbie",
    video = false,
    vote_average = 0.0,
    vote_count = 0,
)

val sampleMovieResponse = MovieResponse(
    results = listOf(sampleMovie),
)

val sampleMovieData = MovieData(
    id = 0,
    adult = false,
    backdrop_path = "",
    original_language = "en",
    original_title = "Barbie",
    overview = "",
    popularity = 4587.093,
    poster_path = "",
    release_date = "2023-07-19",
    title = "Barbie",
    video = false,
    vote_average = 0.0,
    vote_count = 0,
)

val sampleMovieDetailResponse = MovieDetailResponse(
    adult = false,
    backdrop_path = "/path/to/backdrop.jpg",
    genres = listOf(
        Genre(id = 28, name = "Action"),
        Genre(id = 12, name = "Adventure"),
    ),
    homepage = "https://www.example.com",
    id = 12345,
    imdb_id = "tt1234567",
    original_language = "en",
    original_title = "Sample Movie",
    overview = "This is a sample movie overview.",
    popularity = 7.8,
    poster_path = "/path/to/poster.jpg",
    release_date = "2023-08-01",
    runtime = 120,
    status = "Released",
    title = "Sample Movie Title",
    video = false,
    vote_average = 8.5,
    vote_count = 1000,
)
