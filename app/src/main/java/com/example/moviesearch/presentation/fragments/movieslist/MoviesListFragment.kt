package com.example.moviesearch.presentation.fragments.movieslist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.data.network.api.MovieApiService.Companion.SORT_BY_POPULARITY
import com.example.moviesearch.presentation.MoviesActivity
import com.example.moviesearch.presentation.MoviesViewModel
import com.example.moviesearch.presentation.adapters.MoviesListAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var movieAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    private fun setupAdapter() {
        movieAdapter = MoviesListAdapter {
            findNavController().navigate(
                MoviesListFragmentDirections.actionMoviesListFragmentToDetailFragment()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MoviesActivity).viewModel
        setupRecyclerView()
        observeData()
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getMovies(SORT_BY_POPULARITY).collect {
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
    }


}