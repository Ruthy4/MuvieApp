package com.example.muvies.domain.usecases

import com.example.muvies.domain.repository.MovieRepository
import javax.inject.Inject

class IsFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    fun execute(movieTitle: String): Boolean {
        return repository.isFavouriteMovie(movieTitle)
    }
}
