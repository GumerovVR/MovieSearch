package com.example.moviesearch.presentation.utils

fun Double.checkVoteAverage(): String {
    return if(this == 0.0) "N/A"
    else this.toString()
}