package com.example.muvies.di

import com.example.muvies.data.repository.MovieRepositoryImpl
import com.example.muvies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRepository(): MovieRepository {
        return MovieRepositoryImpl()
    }
}
