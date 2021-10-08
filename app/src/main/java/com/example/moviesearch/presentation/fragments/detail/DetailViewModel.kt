package com.example.moviesearch.presentation.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.utils.mapping.asMovieDB
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.use_cases.AddMovieToDatabaseUseCase
import com.example.moviesearch.domain.use_cases.GetMovieInDatabaseUseCase
import com.example.moviesearch.domain.use_cases.SaveMovieInFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addMovieToDatabaseUseCase: AddMovieToDatabaseUseCase,
    private val getMovieInDatabaseUseCase: GetMovieInDatabaseUseCase,
    private val saveMovieInFavouriteUseCase: SaveMovieInFavouriteUseCase
    ) : ViewModel() {

    private var _movieDB = MutableLiveData<MovieDB?>()
    private val movieDB: MutableLiveData<MovieDB?>
        get() = _movieDB

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite


    fun insertMovieInDB(movie: Movie) {
        viewModelScope.launch {
            addMovieToDatabaseUseCase(movie.asMovieDB())
        }
    }

    fun getMovieFromDB(id: Int) {
        viewModelScope.launch {
            _movieDB.postValue(getMovieInDatabaseUseCase(id))
        }
    }

    fun saveInFavourite() {
        viewModelScope.launch {
            val favourite = movieDB.value
                ?.isFavourite?.let { movieDB.value?.copy(isFavourite = !it) }
            favourite?.let {
                viewModelScope.launch {
                    saveMovieInFavouriteUseCase(it)
                }
                _movieDB.value = it
            }
            setFABIcon()
        }
    }

    private fun setFABIcon() {
        _isFavourite.value = _movieDB.value?.isFavourite
    }

}