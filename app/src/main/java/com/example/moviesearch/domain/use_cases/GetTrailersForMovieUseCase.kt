package com.example.moviesearch.domain.use_cases

import com.example.moviesearch.data.repository.MovieRepositoryImpl
import com.example.moviesearch.domain.entities.Trailer
import javax.inject.Inject

class GetTrailersForMovieUseCase @Inject constructor(
    private val repository: MovieRepositoryImpl
) {
    suspend operator fun invoke(movieId: Int, lang: String): List<Trailer> {
        return repository.getTrailersForMovie(movieId, lang = lang)
    }
}