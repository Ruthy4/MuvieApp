package com.example.muvies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.muvies.data.remote.dto.Movie

@Dao
interface MovieDao {
    @Transaction
    fun updateMovie(movie: List<Movie>?) {
        clearAllMovies()
        insertMovie(movie)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<Movie>?)

    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>

    @Query("DELETE FROM movie WHERE id = :id")
    fun deleteMovie(id: Int)

    @Query("DELETE FROM movie")
    fun clearAllMovies()
}
