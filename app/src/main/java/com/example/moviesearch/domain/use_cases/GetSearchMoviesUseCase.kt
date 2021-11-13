package com.example.moviesearch.domain.use_cases

import androidx.paging.PagingSource
import com.example.moviesearch.data.repository.MovieRepositoryImpl
import com.example.moviesearch.domain.entities.Movie
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    operator fun invoke(query: String, lang: String): PagingSource<Int, Movie> {
        return repository.getSearchMovies(query, lang = lang)
    }
}