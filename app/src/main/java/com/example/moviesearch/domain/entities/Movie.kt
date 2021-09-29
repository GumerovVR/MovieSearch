package com.example.moviesearch.domain.entities

data class Movie(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterSmallSize: String,
    val posterFullSize: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
