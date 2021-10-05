package com.example.moviesearch.presentation.adapters.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.data.utils.mapping.asMovie
import retrofit2.HttpException

class SearchMoviePagingSource(
    private val apiService: MovieApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state
            .closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: START_PAGE

        val response = apiService.getSearchMoviesFromNetwork(
            query = query, page = page
        )


        return if (response.isSuccessful) {
            val movies = checkNotNull(response.body())
                .networkMovies.map { it.asMovie() }
            val nextKey = if (movies.size < DEFAULT_PAGE_SIZE) null else page + 1
            val prevKey = if (page == START_PAGE) null else page - 1
            LoadResult.Page(movies, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }

    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20 // api does not support custom page size
        const val START_PAGE = 1
    }
}