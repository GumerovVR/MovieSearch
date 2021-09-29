package com.example.moviesearch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviesearch.R
import com.example.moviesearch.data.network.ApiFactory
import com.example.moviesearch.databinding.ActivityMainBinding
import com.example.moviesearch.domain.entity.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private var movieList: ArrayList<Movie> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //test api
        val disposable = ApiFactory.apiService.getTopMoviesFromJSON()
            .map { it.movies }
            .map { it[1] }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                movieList.add(it)
            }, {
                it.message?.let { it1 -> Log.d("TEST API", it1) }
            })
        compositeDisposable.add(disposable)
//      Log.d("TEST API", movieList?.size.toString())
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun setupRecyclerView() {
        with(binding.rvMoviesList) {
            moviesListAdapter = MoviesListAdapter()
            moviesListAdapter.submitList(movieList)
            adapter = moviesListAdapter
        }
    }
}