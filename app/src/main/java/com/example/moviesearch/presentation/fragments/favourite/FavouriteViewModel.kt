package com.example.moviesearch.presentation.fragments.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repo: Repository) : ViewModel() {

    fun clearNotFavouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNotFavouriteMovie(false)
        }
    }

    fun getFavouriteMovies() = repo.getAllMoviesDB()

}
