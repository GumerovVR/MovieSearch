package com.example.moviesearch.presentation.fragments.favourite

import androidx.lifecycle.*
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.domain.use_cases.ClearNotFavouriteMoviesUseCase
import com.example.moviesearch.domain.use_cases.GetAllFavouriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getAllFavouriteMoviesUseCase: GetAllFavouriteMoviesUseCase,
    private val deleteNotFavouriteMoviesUseCase: ClearNotFavouriteMoviesUseCase
    ) : ViewModel() {

    fun clearNotFavouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNotFavouriteMoviesUseCase(false)
        }
    }

    fun getFavouriteMovies() = getAllFavouriteMoviesUseCase()

}
