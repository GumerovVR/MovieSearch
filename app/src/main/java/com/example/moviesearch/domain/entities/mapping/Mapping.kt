package com.example.moviesearch.domain.entities

import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.network.entities.NetworkMovie

fun List<NetworkMovie>.asUiModels() = map { it.asUiModel() }

fun NetworkMovie.asUiModel() = Movie(
    id = this.id ?: MovieApiService.UNKNOWN_ID,
    title = this.title ?: "",
    posterSmallSize =
    "${MovieApiService.BASE_URL_POSTER}${MovieApiService.POSTER_SMALL_SIZE}${this.posterPath}" ?: "",
    posterFullSize =
    "${MovieApiService.BASE_URL_POSTER}${MovieApiService.POSTER_FULL_SIZE}${this.posterPath}" ?: "",
    originalTitle = this.originalTitle ?: "",
    overview = this.overview ?: "",
    popularity = this.popularity ?: 0.0,
    releaseDate = this.posterPath ?: "",
    voteAverage = this.voteAverage ?: 0.0,
    voteCount = this.voteCount ?: 0
)