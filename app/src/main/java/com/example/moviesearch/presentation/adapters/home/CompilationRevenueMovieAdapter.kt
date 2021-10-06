package com.example.moviesearch.presentation.adapters.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.moviesearch.domain.entities.Movie

class CompilationRevenueMovieAdapter(private val onClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, HorizontalMovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieViewHolder {
        val viewHolder = HorizontalMovieViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            getItem(viewHolder.absoluteAdapterPosition)?.let { movie -> onClick.invoke(movie) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HorizontalMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}