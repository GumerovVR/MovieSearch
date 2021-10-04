package com.example.moviesearch.presentation.adapters.categoryMovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.utils.checkVoteAverage
import com.example.moviesearch.presentation.utils.setNetworkImage
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(movie: Movie) {
        with(binding) {
            tvTitleName.text = movie.title
            tvMovieRatingScore.text = movie.voteAverage.checkVoteAverage()
            ivSmallPoster.setNetworkImage(movie.posterSmallSize)
        }
    }

    companion object{
        fun create(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(inflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}