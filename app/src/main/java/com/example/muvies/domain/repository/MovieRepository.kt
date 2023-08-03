package com.example.muvies.domain.repository

import com.example.muvies.data.local.MovieData
import io.reactivex.Observable

interface MovieRepository {
    fun getMovieList(): Observable<List<MovieData>>
}
