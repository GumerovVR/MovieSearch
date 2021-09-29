package com.example.moviesearch.data.network.repository

import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.entities.asUiModels

class NetworkRepositoryImpl (private val movieApi: MovieApiService) : NetworkRepository {
    override suspend fun getMovies(sortBy: String, page: Int): List<Movie> {
        return movieApi.getMoviesFromNetwork(sortBy = sortBy, page = page).networkMovies.asUiModels()
    }
}