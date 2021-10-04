package com.example.moviesearch.domain.repository

import com.example.moviesearch.data.local.FavouriteMovieDao
import com.example.moviesearch.data.local.entities.MovieDB

class Repository(
    private val db: FavouriteMovieDao
) {
    suspend fun insert(movieDB: MovieDB){
        db.insert(movieDB)
    }

    suspend fun upsert(movieDB: MovieDB){
        db.upsert(movieDB)
    }

    suspend fun getMovieDB(id: Int): MovieDB{
        return db.getMovieDB(id)
    }

    suspend fun deleteMovieByID(id: Int){
        db.deleteMovieByID(id)
    }
}
