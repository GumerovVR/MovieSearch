package com.example.moviesearch.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearch.data.network.entities.NetworkMovie

class MovieDiffCallback: DiffUtil.ItemCallback<NetworkMovie>() {
    override fun areItemsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NetworkMovie, newItem: NetworkMovie): Boolean {
        return oldItem == newItem
    }
}