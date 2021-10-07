package com.example.moviesearch.domain.use_cases

import androidx.paging.PagingSource
import com.example.moviesearch.data.repository.MovieRepositoryImpl
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.repository.MovieRepository
import javax.inject.Inject

class GetCategoryMoviesUseCase @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    operator fun invoke(sortBy: String): PagingSource<Int, Movie> {
        return repository.getCategoryMovies(sortBy = sortBy)
    }
}