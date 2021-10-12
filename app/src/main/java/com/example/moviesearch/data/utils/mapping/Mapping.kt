package com.example.moviesearch.data.utils.mapping

import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.network.entities.NetworkMovie
import com.example.moviesearch.domain.entities.Movie

fun NetworkMovie.asMovie(): Movie {
    return Movie(
        id = this.id ?: MovieApiService.UNKNOWN_ID,
        title = this.title ?: "",
        posterSmallSize =
        "${MovieApiService.BASE_URL_POSTER}${MovieApiService.POSTER_SMALL_SIZE}${this.posterPath}"
            ?: "",
        posterFullSize =
        "${MovieApiService.BASE_URL_POSTER}${MovieApiService.POSTER_FULL_SIZE}${this.posterPath}"
            ?: "",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0
    )
}

fun Movie.asMovieDB(): MovieDB {
    return MovieDB(
        id, originalTitle, overview, posterSmallSize, posterFullSize,
        releaseDate, title, voteAverage, isFavourite = false
    )
}
fun MovieDB.asMovie(): Movie{
    return Movie(
        id, originalTitle, overview, posterSmallSize, posterFullSize,
        releaseDate, title, voteAverage
    )
}