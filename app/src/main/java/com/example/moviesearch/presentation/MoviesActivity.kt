package com.example.moviesearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.databinding.ActivityMainBinding
import com.example.moviesearch.domain.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = ApiFactory.apiService
        val networkRepo = Repository(api)
        val viewModelProviderFactory = MoviesViewModelProviderFactory(networkRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(MoviesViewModel::class.java)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConf= AppBarConfiguration(setOf(
            R.id.moviesListFragment,
            R.id.favouriteFragment
        ))
        setupActionBarWithNavController(navController, appBarConf)
        navView.setupWithNavController(navController)
    }
}