package com.example.moviesearch.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesearch.data.local.db.FavouriteMovieDao
import com.example.moviesearch.data.local.entities.MovieDB

class Repository(
    private val db: FavouriteMovieDao
) {

    suspend fun insert(movieDB: MovieDB) = db.insert(movieDB)

    suspend fun upsert(movieDB: MovieDB) = db.upsert(movieDB)

    suspend fun getMovieDB(id: Int): MovieDB = db.getMovieDB(id)

    suspend fun deleteMovieByID(id: Int) = db.deleteMovieByID(id)

    fun getAllMoviesDB(): LiveData<List<MovieDB>> = db.getAllMoviesDB()

    suspend fun deleteNotFavouriteMovie(isFavourite: Boolean) =
        db.deleteNotFavouriteMovie(isFavourite)

}
