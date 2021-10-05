package com.example.moviesearch.presentation.fragments.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.movieslist.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val apiService: MovieApiService) : ViewModel() {

    private var currentSortBy: String? = null
    private var currentResult: Flow<PagingData<Movie>>? = null

    fun getMovies(sortBy: String): Flow<PagingData<Movie>> {
        val lastResult = currentResult
        if (lastResult != null && currentSortBy == sortBy) {
            return lastResult
        }
        currentSortBy = sortBy

        val newResult =
            pagingMovies(sortBy).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    private fun pagingMovies(sortBy: String): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = MoviePagingSource.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { MoviePagingSource(apiService, sortBy) }).flow
    }
}