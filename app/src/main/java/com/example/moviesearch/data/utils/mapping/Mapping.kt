package com.example.moviesearch.data.utils.mapping

import com.example.moviesearch.R
import com.example.moviesearch.common.Const
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.network.entities.NetworkMovie
import com.example.moviesearch.data.network.entities.NetworkTrailer
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.entities.Trailer

fun NetworkMovie.asMovie(): Movie {
    return Movie(
        id = this.id ?: MovieApiService.UNKNOWN_ID,
        title = this.title ?: "",
        posterSmallSize =
        "${Const.BASE_URL_POSTER}${Const.POSTER_SMALL_SIZE}${this.posterPath}",
        posterFullSize =
        "${Const.BASE_URL_POSTER}${Const.POSTER_FULL_SIZE}${this.posterPath}",
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

fun NetworkTrailer.asTrailer(): Trailer {
    return Trailer(
        id = this.id,
        name = this.name,
        url = "${Const.BASE_URL_TRAILER}${this.key}",
        thumbnailUrl = "${Const.BASE_URL_TRAILER_THUMBNAIL}${this.key}${Const.BASE_URL_TRAILER_THUMBNAIL_END}"
    )
}

