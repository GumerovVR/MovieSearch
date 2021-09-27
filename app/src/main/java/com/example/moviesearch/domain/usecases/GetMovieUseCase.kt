package com.example.moviesearch.domain.usecases

import com.example.moviesearch.domain.entity.Movie
import com.example.moviesearch.domain.repository.Repository

class GetMovieUseCase(private val repository: Repository) {
    fun getMovie(movieId: Int): Movie {
        return repository.getMovie(movieId)
    }
}