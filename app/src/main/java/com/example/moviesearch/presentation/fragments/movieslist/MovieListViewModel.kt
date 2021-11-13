package com.example.moviesearch.presentation.fragments.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.use_cases.GetCategoryMoviesUseCase
import com.example.moviesearch.data.network.adapters.paging_source.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getCategoryMoviesUseCase: GetCategoryMoviesUseCase
) : ViewModel() {

    private var currentSortBy: String? = null
    private var currentResult: Flow<PagingData<Movie>>? = null

    fun getMovies(sortBy: String): Flow<PagingData<Movie>> {
        val locale = Locale.getDefault().language
        val lastResult = currentResult
        if (lastResult != null && currentSortBy == sortBy) {
            return lastResult
        }
        currentSortBy = sortBy

        val newResult =
            pagingMovies( sortBy, lang = locale).cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    private fun pagingMovies(sortBy: String, lang: String): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = MoviePagingSource.DEFAULT_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { getCategoryMoviesUseCase(sortBy = sortBy, lang = lang) }).flow
    }

}