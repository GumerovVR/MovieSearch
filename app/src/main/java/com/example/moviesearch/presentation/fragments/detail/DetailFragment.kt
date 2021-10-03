package com.example.moviesearch.presentation.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviesearch.R
import com.example.moviesearch.databinding.DetailFragmentBinding
import com.example.moviesearch.databinding.MoviesListFragmentBinding
import com.example.moviesearch.domain.entities.Movie
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding: DetailFragmentBinding
        get() = _binding ?: throw RuntimeException("DetailFragmentBinding == null")

    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = args.movie
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            Picasso.get().load(movie?.posterFullSize).into(ivFullPoster)
            movieInfo.tvTitleName.text = movie?.title
            movieInfo.tvOriginalName.text = movie?.originalTitle
            movieInfo.tvRatingDetail.text = movie?.voteAverage.toString()
            movieInfo.tvOverview.text = movie?.overview
        }
    }
}