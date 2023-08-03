package com.example.muvies.data.repository

import com.example.muvies.data.ApiService
import com.example.muvies.data.local.MovieData
import com.example.muvies.data.local.dao.FavoriteMoviesDao
import com.example.muvies.data.local.dao.MovieDao
import com.example.muvies.util.sampleMovie
import com.example.muvies.util.sampleMovieData
import com.example.muvies.util.sampleMovieResponse
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieRepositoryImplTest {
    @Mock
    lateinit var mockApiService: ApiService

    @Mock
    lateinit var mockMovieDao: MovieDao

    @Mock
    lateinit var favoriteMoviesDao: FavoriteMoviesDao

    private lateinit var serviceUnderTest: MovieRepositoryImpl

    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = MovieRepositoryImpl(mockApiService, mockMovieDao, favoriteMoviesDao)

        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
    }

    @Test
    fun `when getMovieList is called, then return remote list of movies`() {
        val expectedValue = listOf(sampleMovieData)
        whenever(mockApiService.getMovieList())
            .thenReturn(Observable.just(sampleMovieResponse))
        whenever(mockMovieDao.getMovies()).thenReturn(emptyList())

        val result = serviceUnderTest.getMovieList()

        testScheduler.triggerActions()

        val testObserver = TestObserver<List<MovieData>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val movieDataList = testObserver.values()[0]

        assertThat(movieDataList.size, `is`(1))
        assertThat(movieDataList[0].title, `is`(expectedValue[0].title))
        assertThat(movieDataList[0].backdrop_path, `is`(expectedValue[0].backdrop_path))
        assertThat(movieDataList[0].isFavorite, `is`(false))
    }

    @Test
    fun `when saveFavouriteMovie is called and movie is non-favorite, then save the favorite movie`() {
        val movieData = sampleMovieData
        whenever(favoriteMoviesDao.isFavouriteMovie(movieData.title.toString())).thenReturn(false)

        serviceUnderTest.saveFavouriteMovie(movieData)

        verify(favoriteMoviesDao).saveFavorite(MovieData.toMovie(movieData))
    }

    @Test
    fun `when saveFavouriteMovie is called and movie is favorite movie, then do not save`() {
        val movieData = sampleMovieData

        whenever(favoriteMoviesDao.isFavouriteMovie(movieData.title.toString())).thenReturn(true)

        serviceUnderTest.saveFavouriteMovie(movieData)

        verify(favoriteMoviesDao, never()).saveFavorite(MovieData.toMovie(movieData))
    }

    @Test
    fun `when removeFavouriteMovie is called, then remove the favorite movie from the database`() {
        val movieData = sampleMovieData

        serviceUnderTest.removeFavouriteMovie(movieData)

        verify(favoriteMoviesDao).removeFavorite(movieData.title.toString())
    }

    @Test
    fun `when getFavouriteMovie is called, then return the list of favorite movies`() {
        val favoriteMoviesList = listOf(sampleMovie)

        whenever(favoriteMoviesDao.getFavoriteMovies()).thenReturn(favoriteMoviesList)

        val result = serviceUnderTest.getFavouriteMovie().test()

        result.assertValueCount(1)
        result.assertComplete()
        result.assertNoErrors()
    }

    @Test
    fun `when isFavouriteMovie is called, then return false if movie is not a favorite`() {
        val movieTitle = sampleMovieData.title.toString()

        whenever(favoriteMoviesDao.isFavouriteMovie(movieTitle)).thenReturn(false)

        val result = serviceUnderTest.isFavouriteMovie(movieTitle)

        assert(!result)
    }

    @Test
    fun `when isFavouriteMovie is called, then return true if movie is a favorite`() {
        val movieTitle = sampleMovieData.title.toString()

        whenever(favoriteMoviesDao.isFavouriteMovie(movieTitle)).thenReturn(true)

        val result = serviceUnderTest.isFavouriteMovie(movieTitle)

        assert(result)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }
}
