package com.example.moviesearch.presentation.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.moviesearch.R
import com.example.moviesearch.data.local.db.AppDatabase
import com.example.moviesearch.databinding.DetailFragmentBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding: DetailFragmentBinding
        get() = _binding ?: throw RuntimeException("DetailFragmentBinding == null")

    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(layoutInflater)
        setupUI()
        movie?.let { movieToDB(it) }
        fabListener()
        toastListener()
        return binding.root
    }

    private fun movieToDB(movie: Movie) {
        viewModel.insertMovieInDB(movie)
        viewModel.getMovieFromDB(movie.id)
    }

    private fun setupUI() {
        with(binding) {
            movie?.posterFullSize?.let { ivFullPoster.setNetworkImage(it) }
            movieInfo.tvTitleName.text = movie?.title
            movieInfo.tvOriginalName.text = movie?.originalTitle
            movieInfo.tvRatingDetail.text = movie?.voteAverage?.checkVoteAverage()
            movieInfo.tvOverview.text = movie?.overview
            movieInfo.tvDate.text = movie?.releaseDate?.substring(0,4)
        }
    }

    private fun fabListener() {
        binding.fabFavourite.setOnClickListener {
            viewModel.saveInFavourite()
        }
    }

    private fun toastListener() {
        viewModel.isFavourite.observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText(requireContext(), R.string.add_to_favourite, Toast.LENGTH_SHORT).show()
                //Реализовать отображение статуса избранного в UI
            } else {
                Toast.makeText(requireContext(), R.string.delete_favourite, Toast.LENGTH_SHORT).show()
            }
        })
    }

}