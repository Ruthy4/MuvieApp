package com.example.muvies.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muvies.data.local.MovieData
import com.example.muvies.domain.usecases.MovieUseCase
import com.example.muvies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _movieListLiveData: MutableLiveData<Resource<List<MovieData>>> = MutableLiveData()
    val movieListLiveData: LiveData<Resource<List<MovieData>>> get() = _movieListLiveData

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchMovieList()
    }

    private fun fetchMovieList() {
        _movieListLiveData.value = Resource.Loading()

        val disposable = movieUseCase.getMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieList ->
                    _movieListLiveData.value = Resource.Success(movieList)
                },
                { error ->
                    _movieListLiveData.value = Resource.Error(error.message)
                },
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
