package com.example.moviesearch.presentation.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.data.local.FavouriteMovieDao

class DetailViewModelFactory(
    private val favouriteMovieDao: FavouriteMovieDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(favouriteMovieDao) as T
    }
}