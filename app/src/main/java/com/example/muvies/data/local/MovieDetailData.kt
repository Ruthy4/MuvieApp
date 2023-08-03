package com.example.muvies.data.local

import com.example.muvies.data.local.dao.GenreData
import com.example.muvies.data.remote.dto.MovieDetailResponse

data class MovieDetailData(
    val id: Int? = 0,
    val backdrop_path: String? = null,
    val genres: List<GenreData>? = emptyList(),
    val homepage: String? = null,
    val imdb_id: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val runtime: Int? = 0,
    val title: String? = null,
) {
    companion object {
        fun from(movieDetail: MovieDetailResponse): MovieDetailData {
            return MovieDetailData(
                id = movieDetail.id,
                backdrop_path = movieDetail.backdrop_path,
                genres = GenreData.from(movieDetail.genres ?: emptyList()),
                homepage = movieDetail.homepage,
                imdb_id = movieDetail.imdb_id,
                original_title = movieDetail.original_title,
                overview = movieDetail.overview,
                poster_path = movieDetail.poster_path,
                release_date = movieDetail.release_date,
                runtime = movieDetail.runtime,
                title = movieDetail.title,
            )
        }
    }
}
