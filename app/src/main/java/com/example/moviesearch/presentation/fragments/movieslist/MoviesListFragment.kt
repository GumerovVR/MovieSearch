package com.example.moviesearch.presentation.fragments.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.databinding.MoviesListFragmentBinding
import com.example.moviesearch.presentation.adapters.home.MoviesLoaderStateAdapter
import com.example.moviesearch.presentation.adapters.movieslist.MovieListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    private var _binding: MoviesListFragmentBinding? = null
    private val binding: MoviesListFragmentBinding
        get() = _binding ?: throw RuntimeException("MoviesListFragmentBinding == null")

    private val viewModel: MovieListViewModel by lazy {
        val apiService = ApiFactory.apiService
        ViewModelProvider(this, MovieListViewModelFactory(apiService))
            .get(MovieListViewModel::class.java)
    }
    private lateinit var movieListAdapter: MovieListAdapter

    private val args: MoviesListFragmentArgs by navArgs()
    private var compilation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compilation = args.compilationInfo
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAdapter()
        _binding = MoviesListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        movieListAdapter = MovieListAdapter {
            val bundle = Bundle().apply {
                putParcelable("movie", it)
            }
            findNavController().navigate(
                R.id.action_moviesListFragment_to_detailFragment, bundle
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMoviesList.apply {
            adapter = movieListAdapter.apply {
                withLoadStateHeaderAndFooter(
                    header = MoviesLoaderStateAdapter(),
                    footer = MoviesLoaderStateAdapter()
                )
                addLoadStateListener { state ->
                    val refreshState = state.refresh
                    with(binding) {
                        rvMoviesList.isVisible = state.refresh != LoadState.Loading
                        progress.isVisible = state.refresh == LoadState.Loading
                    }
                    if (refreshState is LoadState.Error) {
                        Toast.makeText(requireContext(), R.string.error_load, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        compilation?.let { observeData(it) }
    }



    private fun observeData(sortBy: String) {
        lifecycleScope.launch {
            viewModel.getMovies(sortBy = sortBy).collect {
                val res = it
                movieListAdapter.submitData(res)
            }
        }
    }




}