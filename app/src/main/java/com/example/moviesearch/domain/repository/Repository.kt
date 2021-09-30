package com.example.moviesearch.domain.repository

interface Repository {
    suspend fun getMovies(sortBy: String, page: Int): List<Movie>
}