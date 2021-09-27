package com.example.moviesearch.domain.usecases

import androidx.lifecycle.LiveData
import com.example.moviesearch.domain.entity.Movie
import com.example.moviesearch.domain.repository.Repository

class GetListMoviesUseCase(private val repository: Repository) {
    fun getListMovies(): LiveData<List<Movie>> {
        return repository.getListMovies()
    }
}