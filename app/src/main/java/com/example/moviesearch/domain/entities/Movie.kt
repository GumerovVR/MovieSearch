package com.example.moviesearch.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int = -1,
    val originalTitle: String = "",
    val overview: String = "",
    val posterSmallSize: String = "",
    val posterFullSize: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0
) : Parcelable
