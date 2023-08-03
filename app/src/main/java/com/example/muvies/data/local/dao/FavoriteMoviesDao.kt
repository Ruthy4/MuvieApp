package com.example.muvies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.muvies.data.remote.dto.Movie

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): List<Movie>

    @Query("DELETE FROM movie WHERE title  = :movieTitle")
    fun removeFavorite(movieTitle: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(movie: Movie)

    @Query("SELECT EXISTS (SELECT 1 FROM movie WHERE title = :movieTitle AND isFavorite = 1)")
    fun isFavouriteMovie(movieTitle: String): Boolean
}
