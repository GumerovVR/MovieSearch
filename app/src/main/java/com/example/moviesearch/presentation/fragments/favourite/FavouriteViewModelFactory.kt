package com.example.moviesearch.presentation.fragments.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearch.domain.repository.Repository


class FavouriteViewModelFactory(
    private val repo: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavouriteViewModel(repo) as T
    }
}