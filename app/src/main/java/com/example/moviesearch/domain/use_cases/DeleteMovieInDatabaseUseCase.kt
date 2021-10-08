package com.example.moviesearch.domain.use_cases

import com.example.moviesearch.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class DeleteMovieInDatabaseUseCase  @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteMovieByID(id)
    }
}