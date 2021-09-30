package com.example.moviesearch.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesearch.domain.entities.Movie

class MoviesListAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var onMovieItemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = MovieViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            onMovieItemClickListener?.invoke(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}