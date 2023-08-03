package com.example.muvies.data

import com.example.muvies.data.remote.dto.MovieDetailResponse
import com.example.muvies.data.remote.dto.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie")
    fun getMovieList(): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getSelectedMovieDetail(
        @Path("movie_id") movieId: Int,
    ): Observable<MovieDetailResponse>
}
