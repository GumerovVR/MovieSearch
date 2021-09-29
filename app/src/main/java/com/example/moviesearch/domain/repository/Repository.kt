package com.example.moviesearch.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesearch.data.network.entities.NetworkMovie
import com.example.moviesearch.domain.entities.Movie

interface Repository {
    suspend fun getMovies(sortBy: String, page: Int): List<Movie>
}