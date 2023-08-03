package com.example.muvies.data.repository

import com.example.muvies.data.ApiService
import com.example.muvies.data.local.MovieData
import com.example.muvies.data.local.MovieDetailData
import com.example.muvies.data.local.dao.MovieDao
import com.example.muvies.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
) : MovieRepository {
    override fun getMovieList(): Observable<List<MovieData>> {
        val localData = movieDao.getMovies()
        val localUserResult = MovieData.from(localData)
        val localObservable = Observable.just(localUserResult)

        val remoteObservable = apiService.getMovieList()
            .flatMap {
                val remoteMovies = it.results
                movieDao.updateMovie(remoteMovies)
                Observable.just(remoteMovies?.let { movieList -> MovieData.from(movieList) })
            }
            .onErrorResumeNext { t: Throwable ->
                return@onErrorResumeNext Observable.just(localUserResult)
            }
        return if (localUserResult.isNotEmpty()) {
            localObservable
        } else {
            remoteObservable
        }
    }

    override fun getSelectedMovieDetail(movieId: Int): Observable<MovieDetailData> {
        val remoteObservable = apiService.getSelectedMovieDetail(movieId)
            .flatMap {
                Observable.just(MovieDetailData.from(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return remoteObservable
    }
}
