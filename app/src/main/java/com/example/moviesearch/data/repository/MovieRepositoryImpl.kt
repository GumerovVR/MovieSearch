package com.example.moviesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.moviesearch.data.local.db.FavouriteMovieDao
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.utils.mapping.asMovie
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.repository.MovieRepository
import com.example.moviesearch.presentation.adapters.home.MoviePagingSource
import com.example.moviesearch.presentation.adapters.search.SearchMoviePagingSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pagingMoviesFactory: MoviePagingSource.Factory,
    private val pagingSearchMoviesFactory: SearchMoviePagingSource.Factory,
    private val db: FavouriteMovieDao
) : MovieRepository {

    fun getCategoryMovies(sortBy: String): PagingSource<Int, Movie> {
        return pagingMoviesFactory.create(sortBy)
    }

    fun getSearchMovies(query: String): PagingSource<Int, Movie> {
        return pagingSearchMoviesFactory.create(query)
    }

    override suspend fun AddMovieToDB(movieDB: MovieDB) {
        db.insert(movieDB)
    }

    override suspend fun SaveMovieInFavourite(movieDB: MovieDB) {
        db.upsert(movieDB)
    }

    override suspend fun getMovieInDB(id: Int): MovieDB {
        return db.getMovieDB(id)
    }

    override suspend fun deleteMovieByID(id: Int) {
        db.deleteMovieByID(id)
    }

    override fun getAllFavouriteMovies(): LiveData<List<MovieDB>> {
        return db.getAllMoviesDB()
    }

    override suspend fun ClearNotFavouriteMovies(isFavourite: Boolean) {
        db.deleteNotFavouriteMovie(isFavourite)
    }
}