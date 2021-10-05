package com.example.moviesearch.presentation.fragments.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviesearch.R
import com.example.moviesearch.data.local.db.AppDatabase
import com.example.moviesearch.data.utils.mapping.asMovie
import com.example.moviesearch.databinding.FavouriteFragmentBinding
import com.example.moviesearch.domain.repository.Repository
import com.example.moviesearch.presentation.adapters.favourite.FavouriteAdapter

class FavouriteFragment : Fragment() {

    private var _binding: FavouriteFragmentBinding? = null
    private val binding: FavouriteFragmentBinding
        get() = _binding ?: throw RuntimeException("FavouriteFragmentBinding == null")

    private val viewModel: FavouriteViewModel by lazy {
        val db = AppDatabase
            .invoke(requireContext()).getFavouriteMovieDao()
        val repo = Repository(db)
        ViewModelProvider(this, FavouriteViewModelFactory(repo))
            .get(FavouriteViewModel::class.java)
    }
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAdapter()
        _binding = FavouriteFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupAdapter() {
        favouriteAdapter = FavouriteAdapter {
            val bundle = Bundle().apply {
                putParcelable("movie", it.asMovie())
            }
            findNavController().navigate(
                R.id.action_favouriteFragment_to_detailFragment, bundle
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavouriteList.apply {
            adapter = favouriteAdapter
        }
        viewModel.clearNotFavouriteMovies()
        observeData()
    }

    private fun observeData() {
        viewModel.getFavouriteMovies()
            .observe(viewLifecycleOwner, Observer {
                favouriteAdapter.submitList(it)
            })
    }
}