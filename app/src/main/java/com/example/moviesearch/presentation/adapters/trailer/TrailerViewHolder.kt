package com.example.moviesearch.presentation.adapters.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.databinding.ItemTrailerBinding
import com.example.moviesearch.domain.entities.Trailer
import com.example.moviesearch.presentation.utils.setNetworkImage

class TrailerViewHolder(private val binding: ItemTrailerBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(trailer: Trailer) {
        with(binding) {
            tvTrailerName.text = trailer.name
            ivTrailerThumbnail.setNetworkImage(trailer.thumbnailUrl)
        }
    }

    companion object {
        fun create(parent: ViewGroup): TrailerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTrailerBinding.inflate(inflater, parent, false)
            return TrailerViewHolder(binding)
        }
    }
}