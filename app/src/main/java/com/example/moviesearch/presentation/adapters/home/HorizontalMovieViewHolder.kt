package com.example.moviesearch.presentation.adapters.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.databinding.MovieItemHorizontalBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage

class HorizontalMovieViewHolder(private val binding: MovieItemHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("ResourceAsColor")
    fun bind(movie: Movie) {
        with(binding) {
            if (movie.voteAverage > 7.0) {
                tvMovieRatingScore.text = movie.voteAverage.checkVoteAverage()
                tvMovieRatingScore.setBackgroundResource(R.color.score_purple)
                ivSmallPoster.setNetworkImage(movie.posterSmallSize)
            } else {
                tvMovieRatingScore.text = movie.voteAverage.checkVoteAverage()
                tvMovieRatingScore.setBackgroundResource(R.color.score_grey)
                ivSmallPoster.setNetworkImage(movie.posterSmallSize)
            }
        }
    }

    companion object{
        fun create(parent: ViewGroup): HorizontalMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemHorizontalBinding.inflate(
                inflater, parent, false)
            return HorizontalMovieViewHolder(binding)
        }
    }
}