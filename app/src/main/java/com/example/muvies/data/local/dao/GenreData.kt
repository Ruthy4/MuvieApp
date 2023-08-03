package com.example.muvies.data.local.dao

import com.example.muvies.data.remote.dto.Genre

data class GenreData(
    val id: Int?,
    val name: String?,
) {
    companion object {
        fun from(genre: List<Genre>): List<GenreData> {
            return genre.map {
                GenreData(
                    id = it.id,
                    name = it.name,
                )
            }
        }
    }
}
