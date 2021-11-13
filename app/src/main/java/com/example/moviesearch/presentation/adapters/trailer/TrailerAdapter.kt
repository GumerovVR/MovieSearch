package com.example.moviesearch.presentation.adapters.trailer

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesearch.domain.entities.Trailer

class TrailerAdapter(private val onClick: (Trailer) -> Unit) :
    ListAdapter<Trailer, TrailerViewHolder>(TrailerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val viewHolder = TrailerViewHolder.create(parent)
        viewHolder.itemView.setOnClickListener {
            getItem(viewHolder.absoluteAdapterPosition)?.let { trailer -> onClick.invoke(trailer) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}