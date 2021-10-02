package com.example.moviesearch.presentation.adapters.categoryMovies

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearch.domain.entities.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}