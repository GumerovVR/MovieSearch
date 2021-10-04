package com.example.moviesearch.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesearch.data.local.entities.MovieDB

@Dao
interface FavouriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieDB: MovieDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movieDB: MovieDB)

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    suspend fun getMovieDB(id: Int): MovieDB

    @Transaction
    suspend fun insertAndReturnMovieDB(movieDB: MovieDB): MovieDB {
        insert(movieDB)
        return getMovieDB(movieDB.id)
    }

    @Query("SELECT * FROM favourite_movies WHERE isFavourite = :isFavourite")
    suspend fun getIsFavouriteMovie(isFavourite: Boolean): MovieDB

    @Query("SELECT * FROM favourite_movies")
    fun getAllMoviesDB(): LiveData<List<MovieDB>>

//    @Query("SELECT * FROM favourite_movies WHERE isFavourite = :isFavourite")
//    fun getAllFavouriteMoviesDB(isFavourite: Boolean): LiveData<List<MovieDB>>

    @Delete
    suspend fun deleteMovieDB(movieDB: MovieDB)

    @Query("DELETE FROM favourite_movies WHERE isFavourite = :isFavourite")
    suspend fun deleteNotFavouriteMovie(isFavourite: Boolean)

    @Query("DELETE FROM favourite_movies WHERE id = :id")
    suspend fun deleteMovieByID(id: Int)

}