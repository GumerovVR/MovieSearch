package com.example.moviesearch.presentation.utils

import android.widget.TextView

fun Double.checkVoteAverage(): String {
    return if(this == 0.0) "N/A"
    else this.toString()
}