package com.example.moviesearch.presentation.adapters.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.data.utils.mapping.asMovie
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class MoviePagingSource @AssistedInject constructor(
    private val apiService: MovieApiService,
    @Assisted("sortBy") private val sortBy: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state
            .closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: START_PAGE
            val response = apiService.getMoviesFromNetwork(
                sortBy = sortBy, page = page
            )
            if (response.isSuccessful) {
                val movies = checkNotNull(response.body())
                    .networkMovies.map { it.asMovie() }
                val nextKey = if (movies.size < DEFAULT_PAGE_SIZE) null else page + 1
                val prevKey = if (page == START_PAGE) null else page - 1
                LoadResult.Page(movies, prevKey, nextKey)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20 // api does not support custom page size
        const val START_PAGE = 1
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("sortBy") sortBy: String): MoviePagingSource
    }
}