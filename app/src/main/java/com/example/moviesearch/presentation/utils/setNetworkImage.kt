package com.example.moviesearch.presentation.utils

import android.widget.ImageView
import com.example.moviesearch.R
import com.squareup.picasso.Picasso

fun ImageView.setNetworkImage(url: String) {
    Picasso.get().load(url)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_image_not_supported_24)
        .into(this)
}