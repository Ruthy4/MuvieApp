package com.example.muvies.data

import com.example.muvies.data.remote.dto.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("discover/movie")
    fun getMovieList(): Observable<MovieResponse>

}
