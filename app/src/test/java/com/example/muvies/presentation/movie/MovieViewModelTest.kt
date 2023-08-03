package com.example.muvies.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.muvies.domain.repository.MovieRepository
import com.example.muvies.domain.usecases.MovieUseCase
import com.example.muvies.util.sampleMovieData
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    var testSchedulerRule = RxImmediateSchedulerRule()
    private val testScheduler = TestScheduler()

    @Mock
    lateinit var movieRepository: MovieRepository

    private lateinit var serviceUnderTest: MovieViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val movieUseCase = MovieUseCase(movieRepository)

        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }

        serviceUnderTest = MovieViewModel(movieUseCase)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }

    @Test
    fun `when getMovies is called then return list of movies`() {
        val movieList = listOf(sampleMovieData)
        whenever(movieRepository.getMovieList()).thenReturn(Observable.just(movieList))

        testScheduler.triggerActions()
//        verify(movieRepository).getMovieList()
    }
}
