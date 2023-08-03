package com.example.muvies.domain.repository

import com.example.muvies.data.local.MovieData
import com.example.muvies.data.local.MovieDetailData
import io.reactivex.Observable

interface MovieRepository {
    fun getMovieList(): Observable<List<MovieData>>

    fun getSelectedMovieDetail(movieId: Int): Observable<MovieDetailData>
}
