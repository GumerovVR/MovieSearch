package com.example.moviesearch.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.domain.entities.Movie

interface MovieRepository {
    suspend fun AddMovieToDB(movieDB: MovieDB)

    suspend fun SaveMovieInFavourite(movieDB: MovieDB)

    suspend fun getMovieInDB(id: Int): MovieDB

    suspend fun deleteMovieByID(id: Int)

    fun getAllFavouriteMovies(): LiveData<List<MovieDB>>

    suspend fun ClearNotFavouriteMovies(isFavourite: Boolean)

}