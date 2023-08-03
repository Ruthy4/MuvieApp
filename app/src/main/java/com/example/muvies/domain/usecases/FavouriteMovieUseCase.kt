package com.example.muvies.domain.usecases

import com.example.muvies.data.local.MovieData
import com.example.muvies.domain.repository.MovieRepository
import javax.inject.Inject

class FavouriteMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun execute(movieData: MovieData, isFavourite: Boolean) {
        when {
            (isFavourite && !movieRepository.isFavouriteMovie(movieData.title.toString())) -> {
                movieRepository.saveFavouriteMovie(movieData)
            }
            (!isFavourite && movieRepository.isFavouriteMovie(movieData.title.toString())) -> {
                movieRepository.removeFavouriteMovie(movieData)
            }
        }
        return movieRepository.saveFavouriteMovie(movieData)
    }
}
