package com.example.moviesearch.presentation.adapters.movieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage

class VerticalMovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(movie: Movie) {
        with(binding) {
            tvTitleName.text = movie.title
            tvMovieRatingScore.text = movie.voteAverage.checkVoteAverage()
            ivSmallPoster.setNetworkImage(movie.posterSmallSize)
        }
    }

    companion object{
        fun create(parent: ViewGroup): VerticalMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(inflater, parent, false)
            return VerticalMovieViewHolder(binding)
        }
    }
}