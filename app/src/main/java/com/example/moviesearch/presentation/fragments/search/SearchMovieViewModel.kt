package com.example.moviesearch.presentation.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.categoryMovies.MoviePagingSource
import com.example.moviesearch.presentation.adapters.searchMovie.SearchMoviePagingSource
import kotlinx.coroutines.flow.Flow

class SearchMovieViewModel(private val apiService: MovieApiService) : ViewModel() {
    private var currentQuery: String? = null
    private var currentResult: Flow<PagingData<Movie>>? = null

    fun getMovies(query: String): Flow<PagingData<Movie>> {
        val lastResult = currentResult
        if (lastResult != null && currentQuery == query) {
            return lastResult
        }
        currentQuery = query

        val newResult =
            pagingSearchMovies(query).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    private fun pagingSearchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = MoviePagingSource.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { SearchMoviePagingSource(apiService, query) }).flow
    }
}