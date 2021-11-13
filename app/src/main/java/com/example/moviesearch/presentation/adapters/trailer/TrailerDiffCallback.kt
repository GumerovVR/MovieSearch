package com.example.moviesearch.presentation.adapters.trailer

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearch.domain.entities.Trailer

class TrailerDiffCallback: DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}