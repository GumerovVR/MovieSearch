package com.example.moviesearch.presentation.adapters.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.data.local.entities.MovieDB
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage


class FavouriteViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(movieDB: MovieDB) {
        with(binding) {
            if (movieDB.voteAverage > 7.0) {
                tvTitleName.text = movieDB.title
                tvMovieRatingScore.text = movieDB.voteAverage.checkVoteAverage()
                tvMovieRatingScore.setBackgroundResource(R.color.score_purple)
                ivSmallPoster.setNetworkImage(movieDB.posterSmallSize)
            } else {
                tvTitleName.text = movieDB.title
                tvMovieRatingScore.text = movieDB.voteAverage.checkVoteAverage()
                tvMovieRatingScore.setBackgroundResource(R.color.score_grey)
                ivSmallPoster.setNetworkImage(movieDB.posterSmallSize)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): FavouriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(
                inflater, parent, false)
            return FavouriteViewHolder(binding)
        }
    }
}