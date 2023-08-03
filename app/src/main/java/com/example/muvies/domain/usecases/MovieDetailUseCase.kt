package com.example.muvies.domain.usecases

import com.example.muvies.data.local.MovieDetailData
import com.example.muvies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun getMovieDetail(movieId: Int): Observable<MovieDetailData> {
        return movieRepository.getSelectedMovieDetail(movieId)
    }
}
