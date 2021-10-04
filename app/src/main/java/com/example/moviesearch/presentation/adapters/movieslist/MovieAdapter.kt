package com.example.moviesearch.presentation.adapters.movieslist

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.moviesearch.domain.entities.Movie

class MovieAdapter(private val onClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = MovieViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            getItem(viewHolder.absoluteAdapterPosition)?.let { movie -> onClick.invoke(movie) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}