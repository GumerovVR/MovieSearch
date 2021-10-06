package com.example.moviesearch.presentation.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.data.network.api.MovieApiService


class SearchMovieViewModelFactory(
    private val apiService: MovieApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchMovieViewModel(apiService) as T
    }
}