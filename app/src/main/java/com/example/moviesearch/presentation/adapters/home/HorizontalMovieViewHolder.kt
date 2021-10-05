package com.example.moviesearch.presentation.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.databinding.MovieItemHorizontalBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage

class HorizontalMovieViewHolder(private val binding: MovieItemHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(movie: Movie) {
        with(binding) {
            tvMovieRatingScore.text = movie.voteAverage.checkVoteAverage()
            ivSmallPoster.setNetworkImage(movie.posterSmallSize)
        }
    }

    companion object{
        fun create(parent: ViewGroup): HorizontalMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemHorizontalBinding.inflate(inflater, parent, false)
            return HorizontalMovieViewHolder(binding)
        }
    }
}