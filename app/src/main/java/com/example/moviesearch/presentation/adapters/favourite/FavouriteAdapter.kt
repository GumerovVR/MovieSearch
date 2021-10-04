package com.example.moviesearch.presentation.adapters.favourite

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesearch.data.local.entities.MovieDB

class FavouriteAdapter(private val onClick: (MovieDB) -> Unit) :
    ListAdapter<MovieDB, FavouriteViewHolder>(FavouriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val viewHolder = FavouriteViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            getItem(viewHolder.absoluteAdapterPosition)?.let { movieDB -> onClick.invoke(movieDB) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}