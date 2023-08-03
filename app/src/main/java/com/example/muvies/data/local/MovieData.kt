package com.example.muvies.data.local

import android.os.Parcelable
import com.example.muvies.data.remote.dto.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val id: Int? = null,
    val title: String? = null,
    val adult: Boolean? = false,
    val backdrop_path: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val video: Boolean? = false,
    val vote_average: Double? = null,
    val vote_count: Int? = 0,
    var isFavorite: Boolean = false,
) : Parcelable {
    companion object {
        fun from(movies: List<Movie>): List<MovieData> {
            return movies.map {
                MovieData(
                    id = it.id,
                    title = it.title,
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = it.poster_path,
                    release_date = it.release_date,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count,
                    isFavorite = it.isFavorite,
                )
            }
        }

        fun toMovie(movieData: MovieData): Movie {
            return Movie(
                id = movieData.id,
                title = movieData.title,
                adult = movieData.adult,
                backdrop_path = movieData.backdrop_path,
                original_language = movieData.original_language,
                original_title = movieData.original_title,
                overview = movieData.overview,
                popularity = movieData.popularity,
                poster_path = movieData.poster_path,
                release_date = movieData.release_date,
                video = movieData.video,
                vote_average = movieData.vote_average,
                vote_count = movieData.vote_count,
                isFavorite = movieData.isFavorite,
            )
        }
    }
}
