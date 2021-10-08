package com.example.moviesearch.domain.use_cases

import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.repository.MovieRepositoryImpl
import javax.inject.Inject

class SaveMovieInFavouriteUseCase  @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    suspend operator fun invoke(movieDB: MovieDB) {
        repository.saveMovieInFavourite(movieDB)
    }
}