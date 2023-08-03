package com.example.muvies.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muvies.data.local.MovieData
import com.example.muvies.domain.usecases.GetFavoriteMovieUseCase
import com.example.muvies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavouriteMovieViewModel @Inject constructor(
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
) : ViewModel() {

    private val _favMovieListLiveData: MutableLiveData<Resource<List<MovieData>>> = MutableLiveData()
    val favMovieListLiveData: LiveData<Resource<List<MovieData>>> get() = _favMovieListLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchFavMovieList() {
        _favMovieListLiveData.value = Resource.Loading()

        val disposable = getFavoriteMovieUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { favMovieList ->
                    _favMovieListLiveData.value = Resource.Success(favMovieList)
                },
                { error ->
                    _favMovieListLiveData.value = Resource.Error(error.message)
                },
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
