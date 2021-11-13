package com.example.moviesearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.example.moviesearch.data.local.db.FavouriteMovieDao
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.domain.repository.MovieRepository
import com.example.moviesearch.data.network.adapters.paging_source.MoviePagingSource
import com.example.moviesearch.data.network.adapters.paging_source.SearchMoviePagingSource
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.utils.mapping.asTrailer
import com.example.moviesearch.domain.entities.Trailer
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pagingMoviesFactory: MoviePagingSource.Factory,
    private val pagingSearchMoviesFactory: SearchMoviePagingSource.Factory,
    private val db: FavouriteMovieDao,
    private val api: MovieApiService
) : MovieRepository {

    fun getCategoryMovies(sortBy: String, lang: String): PagingSource<Int, Movie> {
        return pagingMoviesFactory.create(sortBy = sortBy, lang = lang)
    }

    fun getSearchMovies(query: String, lang: String): PagingSource<Int, Movie> {
        return pagingSearchMoviesFactory.create(query, lang = lang)
    }

    override suspend fun getTrailersForMovie(movieId: Int, lang: String): List<Trailer> {
        return try {
            api.getTrailersForMovie(movieId, lang = lang).results.map { it.asTrailer() }
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun addMovieToDB(movieDB: MovieDB) {
        db.insert(movieDB)
    }

    override suspend fun saveMovieInFavourite(movieDB: MovieDB) {
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

    override suspend fun clearNotFavouriteMovies(isFavourite: Boolean) {
        db.deleteNotFavouriteMovie(isFavourite)
    }
}