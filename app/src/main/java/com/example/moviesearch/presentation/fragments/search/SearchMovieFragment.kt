package com.example.moviesearch.presentation.fragments.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesearch.R
import com.example.moviesearch.data.network.api.ApiFactory
import com.example.moviesearch.data.network.api.MovieApiService
import com.example.moviesearch.databinding.MoviesListFragmentBinding
import com.example.moviesearch.databinding.SearchMovieFragmentBinding
import com.example.moviesearch.presentation.adapters.categoryMovies.MovieAdapter
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModel
import com.example.moviesearch.presentation.fragments.movieslist.MovieListViewModelFactory
import com.example.moviesearch.presentation.fragments.movieslist.MoviesListFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchMovieFragment : Fragment() {

    private var _binding: SearchMovieFragmentBinding? = null
    private val binding: SearchMovieFragmentBinding
        get() = _binding ?: throw RuntimeException("SearchMovieFragmentBinding == null")


    private val viewModel: SearchMovieViewModel by lazy {
        val apiService = ApiFactory.apiService
        ViewModelProvider(this, SearchMovieViewModelFactory(apiService))
            .get(SearchMovieViewModel::class.java)
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAdapter()
        _binding = SearchMovieFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        movieAdapter = MovieAdapter {
            findNavController().navigate(
                SearchMovieFragmentDirections.actionSearchMovieFragmentToDetailFragment(it)
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSearchMovie.apply {
            adapter = movieAdapter
        }
        startSearching()
    }

    private fun startSearching() {
        binding.etSearchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searching()
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                true
            } else {
                false
            }
        }
    }

    private fun searching() {
        binding.etSearchMovie.text.trim().let { query ->
            if (query.isNotEmpty()) {
                observeData(query.toString())
            }
        }
    }

    private fun observeData(query: String) {
        lifecycleScope.launch {
            viewModel.getMovies(query = query).collect {
                val res = it
                movieAdapter.submitData(res)
            }
        }
    }
}