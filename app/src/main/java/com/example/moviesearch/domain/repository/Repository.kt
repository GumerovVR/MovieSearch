package com.example.moviesearch.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesearch.domain.entity.Movie

interface Repository {
    fun getListMovies(): LiveData<List<Movie>>

    fun getMovie(movieId: Int): Movie
}