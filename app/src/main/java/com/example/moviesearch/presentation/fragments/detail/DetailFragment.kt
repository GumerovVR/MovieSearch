package com.example.moviesearch.presentation.fragments.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviesearch.R
import com.example.moviesearch.databinding.DetailFragmentBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.trailer.TrailerAdapter
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

    private lateinit var trailerAdapter: TrailerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(layoutInflater)
        movie?.let { observeTrailers(it.id) }
        movie?.let { movieToDB(it) }
        toastListener()
        fabListener()
        setupTrailerAdapter()
        setupUI()
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
            if (movie?.overview?.length ?: 0 > 1){ movieInfo.tvOverview.text = movie?.overview }
            else movieInfo.tvOverview.text = requireContext().getText(R.string.not_overview)
            movieInfo.tvDate.text = movie?.releaseDate?.substring(0,4)
            movieInfo.rvTrailers.adapter = trailerAdapter
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

    private fun setupTrailerAdapter() {
        trailerAdapter = TrailerAdapter {
            val intentToTrailer = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intentToTrailer)
        }
    }

    private fun observeTrailers(movieId: Int) {
        viewModel.getTrailers(movieId)
        viewModel.trailer.observe(viewLifecycleOwner, {
            trailerAdapter.submitList(it)
        })
    }

}