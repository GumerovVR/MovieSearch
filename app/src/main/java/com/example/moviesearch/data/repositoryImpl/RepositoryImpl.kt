package com.example.moviesearch.data.repositoryImpl

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviesearch.data.network.ApiFactory
import com.example.moviesearch.domain.entity.Movie
import com.example.moviesearch.domain.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object RepositoryImpl : Repository {
    override fun getListMovies(): LiveData<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getMovie(movieId: Int): Movie {
        TODO("Not yet implemented")
    }

}