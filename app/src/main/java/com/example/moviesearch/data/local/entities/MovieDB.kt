package com.example.moviesearch.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieDB(
    @PrimaryKey
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterSmallSize: String,
    val posterFullSize: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavourite: Boolean
)
