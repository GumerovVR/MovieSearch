package com.example.moviesearch.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesearch.data.network.entities.NetworkMovie

class MoviesListAdapter : ListAdapter<NetworkMovie, MovieViewHolder>(MovieDiffCallback()) {

    var onMovieItemClickListener: ((NetworkMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = MovieViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            onMovieItemClickListener?.invoke(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}