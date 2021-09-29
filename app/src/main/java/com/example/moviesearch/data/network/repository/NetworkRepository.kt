package com.example.moviesearch.data.network.repository

import com.example.moviesearch.domain.entities.Movie

interface NetworkRepository {
    suspend fun getMovies(sortBy: String, page: Int): List<Movie>
}