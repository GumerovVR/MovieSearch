package com.example.moviesearch.presentation.adapters.categoryMovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.domain.entities.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(movie: Movie) {
        with(binding) {
            tvTitleName.text = movie.title
            Picasso.get().load(movie.posterSmallSize).into(ivSmallPoster)
            tvMovieRatingScore.text = movie.voteAverage.toString()
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