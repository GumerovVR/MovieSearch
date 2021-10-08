package com.example.moviesearch.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class GetAllFavouriteMoviesUseCase  @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    operator fun invoke(): LiveData<List<MovieDB>> {
        return repository.getAllFavouriteMovies()
    }
}