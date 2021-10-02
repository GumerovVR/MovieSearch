package com.example.moviesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.repository.Repository

class MoviesViewModelProviderFactory(
    private val repository: Repository,
    private val apiService: MovieApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository, apiService) as T
    }
}
