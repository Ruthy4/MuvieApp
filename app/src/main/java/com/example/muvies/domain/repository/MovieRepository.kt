package com.example.muvies.domain.repository

import com.example.muvies.data.local.MovieData
import com.example.muvies.data.local.MovieDetailData
import io.reactivex.Observable

interface MovieRepository {
    fun getMovieList(): Observable<List<MovieData>>
    fun getSelectedMovieDetail(movieId: Int): Observable<MovieDetailData>
    fun saveFavouriteMovie(movieData: MovieData)
    fun removeFavouriteMovie(movieData: MovieData)
    fun getFavouriteMovie(): Observable<List<MovieData>>
    fun isFavouriteMovie(movieTitle: String): Boolean
}
