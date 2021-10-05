package com.example.moviesearch.presentation.fragments.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.databinding.HomeFragmentBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.home.CompilationPopularMovieAdapter
import com.example.moviesearch.presentation.adapters.home.CompilationRevenueMovieAdapter
import com.example.moviesearch.presentation.adapters.home.CompilationTopRatingMovieAdapter
import com.example.moviesearch.presentation.adapters.home.MoviesLoaderStateAdapter
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModel
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding
        get() = _binding ?: throw RuntimeException("HomeFragmentBinding == null")

    private val viewModel: MovieListViewModel by lazy {
        val apiService = ApiFactory.apiService
        ViewModelProvider(this, MovieListViewModelFactory(apiService))
            .get(MovieListViewModel::class.java)
    }

    private lateinit var compilationPopularMovieAdapter: CompilationPopularMovieAdapter
    private lateinit var compilationTopRatingMovieAdapter: CompilationTopRatingMovieAdapter
    private lateinit var compilationRevenueMovieAdapter: CompilationRevenueMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAdapters()
        _binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupAdapters() {
        compilationPopularMovieAdapter = CompilationPopularMovieAdapter {
            navigateToDetail(it)
        }
        compilationTopRatingMovieAdapter = CompilationTopRatingMovieAdapter {
            navigateToDetail(it)
        }
        compilationRevenueMovieAdapter = CompilationRevenueMovieAdapter {
            navigateToDetail(it)
        }

    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compilationPopularMovieAdapter.withLoadStateHeader(MoviesLoaderStateAdapter())
        setupRecyclers()
        popularityCompilation()
        topRatingCompilation()
        revenueCompilation()
    }

    private fun setupRecyclers() {
        with(binding){
            rvPopular.apply { adapter = compilationPopularMovieAdapter }
            rvTopRating.apply { adapter = compilationTopRatingMovieAdapter }
            rvRevenue.apply { adapter = compilationRevenueMovieAdapter }
        }
    }

    private fun popularityCompilation() {
        val sortBy = MovieApiService.SORT_BY_POPULARITY
        observeData(sortBy)
        binding.ivAllPopular.setOnClickListener {
            navigateToMovieList(sortBy)
        }
    }

    private fun topRatingCompilation() {
        val sortBy = MovieApiService.SORT_BY_TOP_RATED
        observeData(sortBy)
        binding.ivAllTopRating.setOnClickListener {
            navigateToMovieList(sortBy)
        }
    }

    private fun revenueCompilation() {
        val sortBy = MovieApiService.SORT_BY_REVENUE
        observeData(sortBy)
        binding.ivAllRevenue.setOnClickListener {
            navigateToMovieList(sortBy)
        }
    }

    private fun navigateToMovieList(sendData: String){
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMoviesListFragment(sendData)
        )
    }

    private fun observeData(sortBy: String) {
        lifecycleScope.launch {
            viewModel.getMovies(sortBy = sortBy).collect {
                val res = it
                when(sortBy) {
                    MovieApiService.SORT_BY_POPULARITY ->
                        compilationPopularMovieAdapter.submitData(res)
                    MovieApiService.SORT_BY_TOP_RATED ->
                        compilationTopRatingMovieAdapter.submitData(res)
                    MovieApiService.SORT_BY_REVENUE ->
                        compilationRevenueMovieAdapter.submitData(res)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}