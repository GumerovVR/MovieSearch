package com.example.moviesearch.domain.use_cases

import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class GetMovieInDatabaseUseCase  @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    suspend operator fun invoke(id: Int): MovieDB {
        return repository.getMovieInDB(id)
    }
}