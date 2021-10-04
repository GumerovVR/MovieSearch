package com.example.moviesearch.presentation.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.utils.mapping.asMovieDB
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.repository.Repository
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: Repository) : ViewModel() {

    private var _movieDB = MutableLiveData<MovieDB>()
    private val movieDB: LiveData<MovieDB>
        get() = _movieDB

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite


    fun insertMovieInDB(movie: Movie){
        viewModelScope.launch {
            repo.insert(movie.asMovieDB())
        }
    }

    fun getMovieFromDB(id: Int) {
        viewModelScope.launch {
            _movieDB.postValue(repo.getMovieDB(id))
        }
    }

    fun saveInFavourite() {
        viewModelScope.launch {
            val favourite = movieDB.value
                ?.isFavourite?.let { movieDB.value?.copy(isFavourite = !it) }
            favourite?.let {
                viewModelScope.launch {
                    repo.upsert(it)
                }
                _movieDB.value = it
            }
            setFABIcon()
        }
    }

    private fun setFABIcon() {
        _isFavourite.value = _movieDB.value?.isFavourite
    }

    private fun clearTempMovie(){
        if(_isFavourite.value == false) {
            viewModelScope.launch {
                movieDB.value?.let { repo.deleteMovieByID(it.id) }
            }
        }
    }
}