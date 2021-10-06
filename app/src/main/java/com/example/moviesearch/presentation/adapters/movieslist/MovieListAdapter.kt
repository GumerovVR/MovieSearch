package com.example.moviesearch.presentation.adapters.movieslist

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.moviesearch.domain.entities.Movie
import com.example.moviesearch.presentation.adapters.home.MovieDiffCallback

class MovieListAdapter(private val onClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, VerticalMovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalMovieViewHolder {
        val viewHolder = VerticalMovieViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            getItem(viewHolder.absoluteAdapterPosition)?.let { movie -> onClick.invoke(movie) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: VerticalMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}