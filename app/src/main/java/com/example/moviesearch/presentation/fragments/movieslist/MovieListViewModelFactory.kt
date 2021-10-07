package com.example.moviesearch.presentation.fragments.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.domain.repository.Repository

class MovieListViewModelFactory(
    private val apiService: MovieApiService,
    private val repo: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(apiService, repo) as T
    }
}