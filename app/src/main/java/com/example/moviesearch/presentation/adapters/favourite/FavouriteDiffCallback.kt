package com.example.moviesearch.presentation.adapters.favourite

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearch.data.local.entities.MovieDB


class FavouriteDiffCallback: DiffUtil.ItemCallback<MovieDB>() {
    override fun areItemsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
        return oldItem == newItem
    }
}