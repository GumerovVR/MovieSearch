package com.example.moviesearch.presentation.fragments.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.data.network.api.MovieApiService.Companion.SORT_BY_POPULARITY
import com.example.moviesearch.databinding.MoviesListFragmentBinding
import com.example.moviesearch.presentation.adapters.categoryMovies.MovieAdapter
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
    private lateinit var movieAdapter: MovieAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
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
        movieAdapter = MovieAdapter {
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
//        viewModel = ViewModelProvider(this, MovieListViewModelFactory(ApiFactory.apiService)).get(MovieListViewModel::class.java)
//            .get(MovieListViewModel::class.java)
//        viewModel = (activity as ActivityMain).viewModel
        binding.rvMoviesList.apply {
            adapter = movieAdapter
        }
        observeData()
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getMovies(sortBy = SORT_BY_POPULARITY).collect {
                val res = it
                movieAdapter.submitData(res)
            }
        }
    }

//    private fun initRecyclerView() {
//        binding.rvNews.apply {
//            adapter = newsAdapter.withLoadStateHeaderAndFooter(
//                header = NewsLoadStateAdapter { newsAdapter.retry() },
//                footer = NewsLoadStateAdapter { newsAdapter.retry() }
//            )
//        }
//        newsAdapter.loadStateListener(binding, requireContext())
//    }


}