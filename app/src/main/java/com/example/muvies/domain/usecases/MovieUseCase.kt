package com.example.muvies.domain.usecases

import com.example.muvies.data.local.MovieData
import com.example.muvies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun getMovieList(): Observable<List<MovieData>> {
        return movieRepository.getMovieList()
    }
}
