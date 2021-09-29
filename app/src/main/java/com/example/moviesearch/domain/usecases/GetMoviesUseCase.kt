package com.example.moviesearch.domain.usecases

import com.example.moviesearch.data.network.entities.NetworkMovie
import com.example.moviesearch.data.network.repository.NetworkRepository
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.repository.Repository

class GetMoviesUseCase(private val repository: NetworkRepository) : Repository {
    override suspend fun getMovies(sortBy: String, page: Int): List<Movie> {
        return repository.getMovies(sortBy, page)
    }
}