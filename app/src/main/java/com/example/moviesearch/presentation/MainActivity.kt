package com.example.moviesearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviesearch.R
import com.example.moviesearch.data.network.entities.NetworkMovie
import com.example.moviesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var networkMovieList: ArrayList<NetworkMovie> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConf= AppBarConfiguration(setOf(
            R.id.moviesListFragment,
            R.id.favouriteFragment
        ))
        setupActionBarWithNavController(navController, appBarConf)
        navView.setupWithNavController(navController)
    }

//    private fun setupRecyclerView() {
//        with(binding.rvMoviesList) {
//            moviesListAdapter = MoviesListAdapter()
//            moviesListAdapter.submitList(networkMovieList)
//            adapter = moviesListAdapter
//        }
//    }
}