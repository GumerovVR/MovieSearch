package com.example.moviesearch.presentation.fragments.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.databinding.HomeFragmentBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.home.*
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModel
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModelFactory
import com.google.android.material.snackbar.Snackbar
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
        _binding = HomeFragmentBinding.inflate(layoutInflater)
        setupAdapters()
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
        compilationRevenueMovieAdapter.apply {
            addLoadStateListener { state ->
                val refreshState = state.refresh
                with(binding) {
                    rvPopular.isVisible = state.refresh != LoadState.Loading
                    progressPopular.isVisible = state.refresh == LoadState.Loading
                }
                if (refreshState is LoadState.Error) {
                    Toast.makeText(requireContext(), R.string.errorLoad, Toast.LENGTH_LONG).show()
                }
            }
            withLoadStateHeaderAndFooter(
                header = MoviesLoaderStateAdapter(),
                footer = MoviesLoaderStateAdapter()
            )
        }
        compilationTopRatingMovieAdapter.apply {
            addLoadStateListener { state ->
                with(binding) {
                    rvTopRating.isVisible = state.refresh != LoadState.Loading
                    progressTopRating.isVisible = state.refresh == LoadState.Loading
                }
            }
            withLoadStateHeaderAndFooter(
                header = MoviesLoaderStateAdapter(),
                footer = MoviesLoaderStateAdapter()
            )

        }
        compilationRevenueMovieAdapter.apply {
            addLoadStateListener { state ->
                with(binding) {
                    rvRevenue.isVisible = state.refresh != LoadState.Loading
                    progressRevenue.isVisible = state.refresh == LoadState.Loading
                }
            }
            withLoadStateHeaderAndFooter(
                header = MoviesLoaderStateAdapter(),
                footer = MoviesLoaderStateAdapter()
            )

        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
        popularityCompilation()
        topRatingCompilation()
        revenueCompilation()
    }

    private fun setupRecyclers() {
        with(binding) {
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

    private fun navigateToMovieList(sendData: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMoviesListFragment(sendData)
        )
    }

    private fun observeData(sortBy: String) {
        lifecycleScope.launch {
            viewModel.getMovies(sortBy = sortBy).collect {
                val res = it
                when (sortBy) {
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
