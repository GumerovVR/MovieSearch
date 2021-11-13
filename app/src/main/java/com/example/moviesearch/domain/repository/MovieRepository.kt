package com.example.moviesearch.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.entities.Trailer

interface MovieRepository {
    suspend fun addMovieToDB(movieDB: MovieDB)

    suspend fun saveMovieInFavourite(movieDB: MovieDB)

    suspend fun getMovieInDB(id: Int): MovieDB

    suspend fun deleteMovieByID(id: Int)

    fun getAllFavouriteMovies(): LiveData<List<MovieDB>>

    suspend fun clearNotFavouriteMovies(isFavourite: Boolean)

    suspend fun getTrailersForMovie(movieId: Int, lang: String): List<Trailer>
}