package com.example.moviesearch.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.databinding.MovieItemBinding
import com.example.moviesearch.data.network.entities.NetworkMovie
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(networkMovie: NetworkMovie) {
        with(binding) {
            tvMovieRatingScore.text = networkMovie.voteAverage.toString()
            tvTitleName.text = networkMovie.title
            Picasso.get().load(networkMovie.getSmallSizePosterPatch()).into(ivSmallPoster)
        }
    }

    companion object{
        fun create(parent: ViewGroup): MovieViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(inflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}