package com.example.muvies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.muvies.data.local.dao.MovieDao
import com.example.muvies.data.remote.dto.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false,
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
