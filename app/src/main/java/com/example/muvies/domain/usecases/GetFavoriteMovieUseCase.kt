package com.example.muvies.domain.usecases

import com.example.muvies.data.local.MovieData
import com.example.muvies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    fun execute(): Observable<List<MovieData>> {
        return repository.getFavouriteMovie()
    }
}
