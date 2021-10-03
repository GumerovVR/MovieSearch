package com.example.moviesearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesearch.data.local.entities.FavouriteMovie

@Dao
interface FavouriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteMovie: FavouriteMovie)

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    suspend fun getFavouriteMovie(id: Int): FavouriteMovie

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>>

    @Delete
    suspend fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie)
}