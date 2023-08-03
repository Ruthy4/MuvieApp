package com.example.muvies.presentation.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muvies.data.local.MovieDetailData
import com.example.muvies.domain.usecases.MovieDetailUseCase
import com.example.muvies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
) : ViewModel() {

    private val _movieDetailLiveData: MutableLiveData<Resource<MovieDetailData>> = MutableLiveData()
    val movieDetailLiveData: LiveData<Resource<MovieDetailData>> get() = _movieDetailLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchMovieDetail(movieId: Int) {
        _movieDetailLiveData.value = Resource.Loading()

        val disposable = movieDetailUseCase.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieDetail ->
                    _movieDetailLiveData.value = Resource.Success(movieDetail)
                },
                { error ->
                    _movieDetailLiveData.value = Resource.Error("${error.message}")
                },
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
